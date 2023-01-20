package com.xml.patent.repository;

import com.xml.patent.util.AuthUtil;
import com.xml.patent.util.SparqlUtil;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.xalan.xsltc.trax.TransformerFactoryImpl;
import org.springframework.core.io.InputStreamResource;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericMetadataRepository {

    TransformerFactory transformerFactory;
    final String XSLT_PATH;
    final String RDF_PATH;
    final String SPARQL_NAMED_GRAPH_URI;
    static final String JSON_TEMP = "src/main/resources/rdf/temp.json";
    static final String RDF_TEMP = "src/main/resources/rdf/temp.rdf";

    public GenericMetadataRepository(String XSLT_PATH, String RDF_PATH, String SPARQL_NAMED_GRAPH_URI) {
        this.XSLT_PATH = XSLT_PATH;
        this.RDF_PATH = RDF_PATH;
        this.SPARQL_NAMED_GRAPH_URI = SPARQL_NAMED_GRAPH_URI;
        transformerFactory = new TransformerFactoryImpl();
    }

    public InputStreamResource getAllAsJSON() throws Exception {
        var conn = AuthUtil.loadFusekiProperties();
        String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, "?s ?p ?o");
        var query = QueryExecution.service(conn.queryEndpoint).query(sparqlQuery).build();
        var results = query.execSelect();
        var file = new File(JSON_TEMP);
        var out = new FileOutputStream(file);
        ResultSetFormatter.outputAsJSON(out, results);
        var resource = new InputStreamResource(new FileInputStream(file));
        query.close();
        return resource;
    }

    public InputStreamResource getAllAsRDF() throws Exception {
        var conn = AuthUtil.loadFusekiProperties();
        String sparqlQuery = SparqlUtil.constructData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, "?s ?p ?o");
        var query = QueryExecution.service(conn.queryEndpoint).query(sparqlQuery).build();
        var model = query.execConstruct();
        var file = new File(RDF_TEMP);
        var out = new FileOutputStream(file);
        model.write(out, SparqlUtil.NTRIPLES);
        var resource = new InputStreamResource(new FileInputStream(file));
        query.close();
        return resource;
    }

    protected void extract(InputStream inputStream) throws Exception {
        var conn = AuthUtil.loadFusekiProperties();
        extractMetadata(inputStream, new FileOutputStream(RDF_PATH));
        var model = ModelFactory.createDefaultModel();
        model.read(RDF_PATH);
        var out = new ByteArrayOutputStream();
        model.write(out, SparqlUtil.NTRIPLES);
        model.write(System.out, SparqlUtil.RDF_XML);
        String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, out.toString());
        var update = UpdateFactory.create(sparqlUpdate);
        var processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
        processor.execute();
        String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, "?s ?p ?o");
        var query = QueryExecution.service(conn.queryEndpoint).query(sparqlQuery).build();
        var results = query.execSelect();
        ResultSetFormatter.out(System.out, results);
        query.close();
    }

    protected void extractMetadata(InputStream in, OutputStream out) throws Exception {
        var transformSource = new StreamSource(new File(XSLT_PATH));
        var rdfTransformer = transformerFactory.newTransformer(transformSource);
        rdfTransformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        rdfTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
        var source = new StreamSource(in);
        var result = new StreamResult(out);
        rdfTransformer.transform(source, result);
    }

    protected String generateSimpleMetadataSearchQuery(String name, String value) throws Exception {
        var conn = AuthUtil.loadFusekiProperties();
        return String.format("""
                        SELECT * FROM <%s>
                        WHERE {\s
                        \t?p1 <http://www.ftn.com/p1/pred/%s> ?%s .
                        \t?p1 <http://www.ftn.com/p1/pred/Naziv_fajla> ?Naziv_fajla .
                        \tFILTER(?%s = "%s")
                        }""",
                conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, name, name, name, value);
    }

    protected String generateAdvancedMetadataSearchQuery(List<String> operators, List<List<String>> statements) throws Exception {
        var conn = AuthUtil.loadFusekiProperties();
        var filterBuilder = new StringBuilder("FILTER(");
        var queryBuilder = new StringBuilder(String.format("""
                        SELECT * FROM <%s>
                        WHERE {
                        """,
                conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI));
        var stmCnt = 0;
        for (var op : operators) {
            var st = statements.get(stmCnt);
            var value = st.get(2).startsWith("\"") || st.get(2).startsWith("'") ? st.get(2) : String.format("\"%s\"", st.get(2));
            filterBuilder.append(String.format("?%s %s %s %s ", st.get(0), st.get(1), value, op));
            queryBuilder.append(String.format("\t?p1 <http://www.ftn.com/p1/pred/%s> ?%s .\n", st.get(0), st.get(0)));
            stmCnt++;
        }
        var st = statements.get(stmCnt);
        var value = st.get(2).startsWith("\"") || st.get(2).startsWith("'") ? st.get(2) : String.format("\"%s\"", st.get(2));
        filterBuilder.append(String.format("?%s %s %s)", st.get(0), st.get(1), value));
        queryBuilder.append(String.format("\t?p1 <http://www.ftn.com/p1/pred/%s> ?%s .\n", st.get(0), st.get(0)));
        queryBuilder.append("\t?p1 <http://www.ftn.com/p1/pred/Naziv_fajla> ?Naziv_fajla .\n");
        queryBuilder.append(filterBuilder).append("\n}");
        System.out.println(queryBuilder);
        return queryBuilder.toString();
    }

    protected List<String> getFileNamesByMetadata(String sparqlQuery) throws Exception {
        var conn = AuthUtil.loadFusekiProperties();
        var query = QueryExecution.service(conn.queryEndpoint).query(sparqlQuery).build();
        var results = query.execSelect();
        RDFNode varValue;
        var retVal = new ArrayList<String>();
        while (results.hasNext()) {
            var querySolution = results.next();
            varValue = querySolution.get("Naziv_fajla");
            retVal.add(varValue.toString());
        }
        return retVal;
    }
}
