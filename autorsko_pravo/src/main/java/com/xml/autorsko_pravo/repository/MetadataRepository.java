package com.xml.autorsko_pravo.repository;

import com.xml.autorsko_pravo.model.Zahtev;
import com.xml.autorsko_pravo.util.AuthUtil;
import com.xml.autorsko_pravo.util.SparqlUtil;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.xalan.xsltc.trax.TransformerFactoryImpl;
import org.springframework.stereotype.Component;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MetadataRepository {

    static final String SPARQL_NAMED_GRAPH_URI = "/metadata";
    static final String XSLT_PATH = "src/main/resources/xslt/metadata.xsl";
    static final String RDF_PATH = "src/main/resources/rdf/a1.rdf";
    TransformerFactory transformerFactory;
    ZahtevRepository zahtevRepository;
    Marshalling marshalling;

    public MetadataRepository(ZahtevRepository zahtevRepository, Marshalling marshalling) {
        transformerFactory = new TransformerFactoryImpl();
        this.zahtevRepository = zahtevRepository;
        this.marshalling = marshalling;
    }

    public void extract(String documentName) throws Exception {
        extract(zahtevRepository.get(documentName));
    }

    public void extract(Zahtev zahtev) throws Exception {
        var conn = AuthUtil.loadFusekiProperties();
        extractMetadata(marshalling.marshallToInputStream(zahtev), new FileOutputStream(RDF_PATH));
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

    public List<Zahtev> simpleMetadataSearch(String name, String value) throws Exception {
        var conn = AuthUtil.loadFusekiProperties();
        String sparqlQuery = String.format("""
                        SELECT * FROM <%s>
                        WHERE {\s
                        \t?a1 <http://www.ftn.com/a1/pred/%s> ?%s .
                        \t?a1 <http://www.ftn.com/a1/pred/Naziv_fajla> ?Naziv_fajla .
                        \tFILTER(?%s = "%s")
                        }""",
                conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, name, name, name, value);
        return searchMetadata(sparqlQuery);
    }

    public List<Zahtev> advancedMetadataSearch(List<String> operators, List<List<String>> statements) throws Exception {
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
            queryBuilder.append(String.format("\t?a1 <http://www.ftn.com/a1/pred/%s> ?%s .\n", st.get(0), st.get(0)));
            stmCnt++;
        }
        var st = statements.get(stmCnt);
        var value = st.get(2).startsWith("\"") || st.get(2).startsWith("'") ? st.get(2) : String.format("\"%s\"", st.get(2));
        filterBuilder.append(String.format("?%s %s %s)", st.get(0), st.get(1), value));
        queryBuilder.append(String.format("\t?a1 <http://www.ftn.com/a1/pred/%s> ?%s .\n", st.get(0), st.get(0)));
        queryBuilder.append("\t?a1 <http://www.ftn.com/a1/pred/Naziv_fajla> ?Naziv_fajla .\n");
        queryBuilder.append(filterBuilder).append("\n}");
        System.out.println(queryBuilder);
        return searchMetadata(queryBuilder.toString());
    }

    private List<Zahtev> searchMetadata(String sparqlQuery) throws Exception {
        var conn = AuthUtil.loadFusekiProperties();
        var query = QueryExecution.service(conn.queryEndpoint).query(sparqlQuery).build();
        var results = query.execSelect();
        RDFNode varValue;
        var retVal = new ArrayList<Zahtev>();
        while (results.hasNext()) {
            var querySolution = results.next();
            varValue = querySolution.get("Naziv_fajla");
            retVal.add(zahtevRepository.get(varValue.toString()));
        }
        return retVal;
    }

    private void extractMetadata(InputStream in, OutputStream out) throws Exception {
        var transformSource = new StreamSource(new File(XSLT_PATH));
        var rdfTransformer = transformerFactory.newTransformer(transformSource);
        rdfTransformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        rdfTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
        var source = new StreamSource(in);
        var result = new StreamResult(out);
        rdfTransformer.transform(source, result);
    }
}
