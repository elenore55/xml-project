package com.xml.zig.repository;

import com.xml.zig.model.Zahtev;
import com.xml.zig.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ZahtevMetadataRepository extends GenericMetadataRepository {

    static final String SPARQL_NAMED_GRAPH_URI = "/metadata";
    static final String XSLT_PATH = "src/main/resources/xslt/zahtev_metadata.xsl";
    static final String RDF_PATH = "src/main/resources/rdf/z1.rdf";
    ZahtevRepository zahtevRepository;
    Marshalling marshalling;

    @Autowired
    public ZahtevMetadataRepository(ZahtevRepository zahtevRepository, Marshalling marshalling) {
        super(XSLT_PATH, RDF_PATH, SPARQL_NAMED_GRAPH_URI);
        this.zahtevRepository = zahtevRepository;
        this.marshalling = marshalling;
    }

    public void extract(String documentName) throws Exception {
        extract(zahtevRepository.get(documentName));
    }

    public void extract(Zahtev zahtev) throws Exception {
        extract(marshalling.marshallToInputStream(zahtev));
    }

    public List<Zahtev> simpleMetadataSearch(String name, String value) throws Exception {
        return searchMetadata(generateSimpleMetadataSearchQuery(name, value));
    }

    public List<Zahtev> advancedMetadataSearch(List<String> operators, List<List<String>> statements) throws Exception {
        return searchMetadata(generateAdvancedMetadataSearchQuery(operators, statements));
    }

    public int countZahtevi(String startDate, String endDate) throws Exception {
        var conn = AuthUtil.loadFusekiProperties();
        String query = String.format("""
                SELECT * FROM <%s>
                WHERE {\s
                \t?z1 <http://www.ftn.com/z1/pred/Datum_podnosenja> ?Datum_podnosenja .
                \t?z1 <http://www.ftn.com/z1/pred/Naziv_fajla> ?Naziv_fajla .
                \tFILTER(?Datum_podnosenja >= "%s" && ?Datum_podnosenja <= "%s")
                }""", conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, startDate, endDate);
        System.out.println(query);
        return searchMetadata(query).size();
    }

    private List<Zahtev> searchMetadata(String sparqlQuery) throws Exception {
        var fileNames = getFileNamesByMetadata(sparqlQuery);
        var retVal = new ArrayList<Zahtev>();
        for (var name : fileNames) {
            retVal.add(zahtevRepository.get(name));
        }
        return retVal;
    }
}
