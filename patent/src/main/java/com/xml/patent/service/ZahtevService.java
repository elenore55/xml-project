package com.xml.patent.service;

import com.xml.patent.model.Zahtev;
import com.xml.patent.repository.Marshalling;
import com.xml.patent.repository.ZahtevMetadataRepository;
import com.xml.patent.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZahtevService {

    MetadataSearchService metadataSearchService;
    ZahtevRepository zahtevRepository;
    ZahtevMetadataRepository zahtevMetadataRepository;
    Marshalling marshalling;

    @Autowired
    public ZahtevService(MetadataSearchService metadataSearchService, ZahtevRepository zahtevRepository,
                         ZahtevMetadataRepository zahtevMetadataRepository, Marshalling marshalling) {
        this.metadataSearchService = metadataSearchService;
        this.zahtevRepository = zahtevRepository;
        this.zahtevMetadataRepository = zahtevMetadataRepository;
        this.marshalling = marshalling;
    }

    public Zahtev getOne(String name) throws Exception {
        return zahtevRepository.get(name);
    }

    public List<Zahtev> getAll() throws Exception {
        return zahtevRepository.getAll();
    }

    public List<Zahtev> search(String text, boolean matchCase) throws Exception {
        return zahtevRepository.search(text, matchCase);
    }

    public void extractMetadata(String name) throws Exception {
        zahtevMetadataRepository.extract(name);
    }

    public List<Zahtev> simpleMetadataSearch(String name, String value) throws Exception {
        return zahtevMetadataRepository.simpleMetadataSearch(name, value);
    }

    public List<Zahtev> advancedMetadataSearch(String rawInput) throws Exception {
        metadataSearchService.generate(rawInput);
        var operators = metadataSearchService.getOperators();
        var statements = metadataSearchService.getStatements();
        return zahtevMetadataRepository.advancedMetadataSearch(operators, statements);
    }

    public void save() throws Exception {
        var zahtev = marshalling.unmarshalFromFile("p1.xml");
        zahtevRepository.save(zahtev);
    }

    public InputStreamResource getAllMetadataAsJSON() throws Exception {
        return zahtevMetadataRepository.getAllAsJSON();
    }

    public InputStreamResource getAllMetadataAsRDF() throws Exception {
        return zahtevMetadataRepository.getAllAsRDF();
    }
}
