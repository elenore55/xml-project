package com.xml.patent.repository;

import com.xml.patent.model.Resenje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResenjeMetadataRepository extends GenericMetadataRepository {

    static final String SPARQL_NAMED_GRAPH_URI = "/resenje_metadata";
    static final String XSLT_PATH = "src/main/resources/xslt/resenje_metadata.xsl";
    static final String RDF_PATH = "src/main/resources/rdf/resenje.rdf";
    ResenjeRepository resenjeRepository;
    Marshalling marshalling;

    @Autowired
    public ResenjeMetadataRepository(ResenjeRepository resenjeRepository, Marshalling marshalling) {
        super(XSLT_PATH, RDF_PATH, SPARQL_NAMED_GRAPH_URI);
        this.resenjeRepository = resenjeRepository;
        this.marshalling = marshalling;
    }

    public void extract(String documentName) throws Exception {
        extract(resenjeRepository.get(documentName));
    }

    public void extract(Resenje resenje) throws Exception {
        extract(marshalling.marshallToInputStream(resenje));
    }

    public List<Resenje> simpleMetadataSearch(String name, String value) throws Exception {
        return searchMetadata(generateSimpleMetadataSearchQuery(name, value));
    }

    public List<Resenje> advancedMetadataSearch(List<String> operators, List<List<String>> statements) throws Exception {
        return searchMetadata(generateAdvancedMetadataSearchQuery(operators, statements));
    }

    private List<Resenje> searchMetadata(String sparqlQuery) throws Exception {
        var fileNames = getFileNamesByMetadata(sparqlQuery);
        var retVal = new ArrayList<Resenje>();
        for (var name : fileNames) {
            retVal.add(resenjeRepository.get(name));
        }
        return retVal;
    }
}
