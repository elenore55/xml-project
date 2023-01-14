package com.xml.autorsko_pravo.repository;

import com.xml.autorsko_pravo.exception.ZahtevAlreadyExistsException;
import com.xml.autorsko_pravo.model.Zahtev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ZahtevRepository extends GenericRepository {

    static final String collectionId = "/db/project/autorsko_pravo";
    Marshalling marshalling;

    @Autowired
    public ZahtevRepository(Marshalling marshalling) {
        super(collectionId);
        this.marshalling = marshalling;
    }

    public void save(Zahtev zahtev) throws Exception {
        String documentName = String.format("Zahtev%d.xml", zahtev.getPopunjavaZavod().getBrojPrijave());
        if (get(documentName) != null)
            throw new ZahtevAlreadyExistsException();
        try (var out = marshalling.marshallToOutputStream(zahtev)) {
            saveResource(documentName, out);
        }
    }

    public Zahtev get(String documentName) throws Exception {
        var resource = getResource(documentName);
        if (resource == null) {
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
