package com.xml.patent.service;

import com.xml.patent.model.Zahtev;
import com.xml.patent.repository.MetadataRepository;
import com.xml.patent.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZahtevService {

    ZahtevRepository zahtevRepository;
    MetadataRepository metadataRepository;

    @Autowired
    public ZahtevService(ZahtevRepository zahtevRepository, MetadataRepository metadataRepository) {
        this.zahtevRepository = zahtevRepository;
        this.metadataRepository = metadataRepository;
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
        metadataRepository.extract(name);
    }
}
