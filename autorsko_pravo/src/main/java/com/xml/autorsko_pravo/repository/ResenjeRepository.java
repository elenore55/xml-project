package com.xml.autorsko_pravo.repository;

import com.xml.autorsko_pravo.exception.DocumentNotFoundException;
import com.xml.autorsko_pravo.exception.ResenjeAlreadyExistsException;
import com.xml.autorsko_pravo.model.Resenje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResenjeRepository extends GenericRepository {

    static final String collectionId = "/db/project/autorsko_pravo/resenje";
    Marshalling marshalling;
    ZahtevRepository zahtevRepository;

    @Autowired
    public ResenjeRepository(Marshalling marshalling, ZahtevRepository zahtevRepository) {
        super(collectionId);
        this.marshalling = marshalling;
        this.zahtevRepository = zahtevRepository;
    }

    public void save(Resenje resenje) throws Exception {
        String zahtevDocumentName = String.format("%s.xml", resenje.getReferenca());
        if (zahtevRepository.get(zahtevDocumentName) == null)
            throw new DocumentNotFoundException();
        String documentName = String.format("Resenje_%s.xml", resenje.getReferenca());
        if (get(documentName) != null)
            throw new ResenjeAlreadyExistsException();
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
