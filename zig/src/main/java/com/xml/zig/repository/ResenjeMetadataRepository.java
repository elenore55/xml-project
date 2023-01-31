package com.xml.zig.repository;

import com.xml.zig.dto.MetadataDTO;
import com.xml.zig.model.Resenje;
import com.xml.zig.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.xml.zig.dto.MetadataDTO.Type.BOOL;
import static com.xml.zig.dto.MetadataDTO.Type.DATE;

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

    public int countOdobreniZahtevi(String startDate, String endDate) throws Exception {
        var conn = AuthUtil.loadFusekiProperties();
        String query = String.format("""
                SELECT * FROM <%s>
                WHERE {\s
                \t?z1 <http://www.ftn.com/z1/pred/Datum_resenja> ?Datum_resenja .
                \t?z1 <http://www.ftn.com/z1/pred/Odobren> ?Odobren .
                \t?z1 <http://www.ftn.com/z1/pred/Naziv_fajla> ?Naziv_fajla .
                \tFILTER(?Odobren = "true" && ?Datum_resenja >= "%s" && ?Datum_resenja <= "%s")
                }""", conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, startDate, endDate);
        System.out.println(query);
        return searchMetadata(query).size();
    }

    public int countOdbijeniZahtevi(String startDate, String endDate) throws Exception {
        var conn = AuthUtil.loadFusekiProperties();
        String query = String.format("""
                SELECT * FROM <%s>
                WHERE {\s
                \t?z1 <http://www.ftn.com/z1/pred/Datum_resenja> ?Datum_resenja .
                \t?z1 <http://www.ftn.com/z1/pred/Odobren> ?Odobren .
                \t?z1 <http://www.ftn.com/z1/pred/Naziv_fajla> ?Naziv_fajla .
                \tFILTER(?Odobren = "false" && ?Datum_resenja >= "%s" && ?Datum_resenja <= "%s")
                }""", conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, startDate, endDate);
        System.out.println(query);
        return searchMetadata(query).size();
    }

    public List<MetadataDTO> getMetadataVariables() {
        return Arrays.asList(
                new MetadataDTO("Naziv_fajla"),
                new MetadataDTO("Datum_resenja", DATE),
                new MetadataDTO("Ime_sluzbenika"),
                new MetadataDTO("Prezime_sluzbenika"),
                new MetadataDTO("Odobren", BOOL)
        );
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
