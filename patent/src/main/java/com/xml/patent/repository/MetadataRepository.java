package com.xml.patent.repository;

import com.xml.patent.model.Zahtev;
import com.xml.patent.util.AuthUtil;
import com.xml.patent.util.SparqlUtil;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.xalan.xsltc.trax.TransformerFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Component
public class MetadataRepository {

    static final String SPARQL_NAMED_GRAPH_URI = "/metadata";
    static final String XSLT_PATH = "src/main/resources/xslt/metadata.xsl";
    static final String RDF_PATH = "src/main/resources/rdf/p1.rdf";
    TransformerFactory transformerFactory;
    ZahtevRepository zahtevRepository;
    Marshalling marshalling;

    @Autowired
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
        var query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        var results = query.execSelect();
        ResultSetFormatter.out(System.out, results);
        query.close();
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
