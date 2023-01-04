package com.xml.zig.repository;

import com.xml.zig.util.AuthUtil;
import com.xml.zig.util.SparqlUtil;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.xalan.xsltc.trax.TransformerFactoryImpl;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class MetadataExtraction {

    static final String SPARQL_NAMED_GRAPH_URI = "/metadata";
    TransformerFactory transformerFactory;
    static final String XSLT_FILE = "src/main/resources/xslt/metadata.xsl";

    public MetadataExtraction() {
        transformerFactory = new TransformerFactoryImpl();
    }

    public void extract() throws Exception {
        var conn = AuthUtil.loadFusekiProperties();
        String xmlFilePath = "src/main/resources/xml/z1.xml";
        String rdfFilePath = "src/main/resources/rdf/z1.rdf";
        extractMetadata(new FileInputStream(xmlFilePath), new FileOutputStream(rdfFilePath));
        var model = ModelFactory.createDefaultModel();
        model.read(rdfFilePath);
        var out = new ByteArrayOutputStream();
        model.write(out, SparqlUtil.NTRIPLES);
        model.write(System.out, SparqlUtil.RDF_XML);
        String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, out.toString());
        var update = UpdateFactory.create(sparqlUpdate);
        var processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
        processor.execute();
        String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, "?s ?p ?o");
        var query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        var results = query.execSelect();
        ResultSetFormatter.out(System.out, results);
        query.close();
    }

    private void extractMetadata(InputStream in, OutputStream out) throws Exception {
        var transformSource = new StreamSource(new File(XSLT_FILE));
        var rdfTransformer = transformerFactory.newTransformer(transformSource);
        rdfTransformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        rdfTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
        var source = new StreamSource(in);
        var result = new StreamResult(out);
        rdfTransformer.transform(source, result);
    }
}
