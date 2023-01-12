package com.xml.zig.repository;

import com.xml.zig.model.Resenje;
import com.xml.zig.model.Zahtev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.base.Collection;
import org.xmldb.api.modules.XMLResource;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResenjeRepository extends GenericRepository {

    static final String collectionId = "/db/project/zig/resenje";
    Collection col;
    XMLResource res;
    Marshalling marshalling;

    @Autowired
    public ResenjeRepository(Marshalling marshalling) {
        super(collectionId);
        this.marshalling = marshalling;
    }

    public void save(Resenje resenje) throws Exception {
        String documentName = String.format("Resenje_%s.xml", resenje.getReferenca());
        try (var out = marshalling.marshallToOutputStream(resenje)) {
            saveResource(documentName, out);
        }
    }

    public Resenje get(String documentName) throws Exception {
        var resource = getResource(documentName);
        if (resource == null) {
            System.out.println("[WARNING] Document '" + documentName + "' cannot be found!");
            return null;
        } else {
            return marshalling.unmarshallResenjeContent(resource.getContentAsDOM());
        }
    }

    public List<Resenje> getAll() throws Exception {
        var result = new ArrayList<Resenje>();
        for (var resource : getAllResources())
            result.add(marshalling.unmarshallResenjeContent(resource.getContentAsDOM()));
        return result;
    }

    public List<Resenje> search(String text, boolean matchCase) throws Exception {
        var result = new ArrayList<Resenje>();
        for (var resource : searchResources(text, matchCase))
            result.add(marshalling.unmarshallResenjeContent(resource.getContentAsDOM()));
        return result;
    }
}
