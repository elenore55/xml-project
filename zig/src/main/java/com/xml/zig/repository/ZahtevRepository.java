package com.xml.zig.repository;

import com.xml.zig.exception.ZahtevAlreadyExistsException;
import com.xml.zig.model.Zahtev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ZahtevRepository extends GenericRepository {

    static final String collectionId = "/db/project/zig";
    Marshalling marshalling;

    @Autowired
    public ZahtevRepository(Marshalling marshalling) {
        super(collectionId);
        this.marshalling = marshalling;
    }

    public void save(Zahtev zahtev) throws Exception {
        String documentName = String.format("Zahtev%d-%d.xml", zahtev.getPopunjavaZavod().getBrojPrijaveZiga().getId(), zahtev.getPopunjavaZavod().getBrojPrijaveZiga().getGodina());
        if (get(documentName) != null)
            throw new ZahtevAlreadyExistsException();
        try (var out = marshalling.marshallToOutputStream(zahtev)) {
            saveResource(documentName, out);
        }
    }

    public Zahtev get(String documentName) throws Exception {
        var resource = getResource(documentName);
        if (resource == null) {
            System.out.println("[WARNING] Document '" + documentName + "' cannot be found!");
            return null;
        } else {
            return marshalling.unmarshallContent(resource.getContentAsDOM());
        }
    }

    public List<Zahtev> getAll() throws Exception {
        var result = new ArrayList<Zahtev>();
        for (var resource : getAllResources())
            result.add(marshalling.unmarshallContent(resource.getContentAsDOM()));
        return result;
    }

    public List<Zahtev> search(String text, boolean matchCase) throws Exception {
        var result = new ArrayList<Zahtev>();
        for (var resource : searchResources(text, matchCase))
            result.add(marshalling.unmarshallContent(resource.getContentAsDOM()));
        return result;
    }
}
