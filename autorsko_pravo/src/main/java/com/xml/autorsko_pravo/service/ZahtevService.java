package com.xml.autorsko_pravo.service;

import com.xml.autorsko_pravo.dto.CreateZahtevDTO;
import com.xml.autorsko_pravo.model.PopunjavaPodnosilac;
import com.xml.autorsko_pravo.model.PopunjavaZavod;
import com.xml.autorsko_pravo.model.Prilog;
import com.xml.autorsko_pravo.model.Zahtev;
import com.xml.autorsko_pravo.repository.Marshalling;
import com.xml.autorsko_pravo.repository.ResenjeRepository;
import com.xml.autorsko_pravo.repository.ZahtevMetadataRepository;
import com.xml.autorsko_pravo.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ZahtevService {

    MetadataSearchService metadataSearchService;
    ZahtevRepository zahtevRepository;
    ZahtevMetadataRepository zahtevMetadataRepository;
    Marshalling marshalling;
    ResenjeRepository resenjeRepository;

    @Autowired
    public ZahtevService(MetadataSearchService metadataSearchService, ZahtevRepository zahtevRepository, Marshalling marshalling,
                         ZahtevMetadataRepository zahtevMetadataRepository,  ResenjeRepository resenjeRepository) {
        this.metadataSearchService = metadataSearchService;
        this.zahtevRepository = zahtevRepository;
        this.zahtevMetadataRepository = zahtevMetadataRepository;
        this.marshalling = marshalling;
        this.resenjeRepository = resenjeRepository;
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

    public void save(CreateZahtevDTO dto, String prilog) throws Exception {
        var popunjavaPodnosilac = new PopunjavaPodnosilac(dto);
        var popunjavaZavod = new PopunjavaZavod();
        popunjavaZavod.setBrojPrijave(zahtevRepository.generateNextId());
        popunjavaZavod.setDatumPodnosenja(new Date());
        var primerDela = new Prilog.PrimerDela();
        primerDela.setPrimer(prilog);
        popunjavaZavod.setPrilog(primerDela);
        var zahtev = new Zahtev();
        zahtev.setPopunjavaPodnosilac(popunjavaPodnosilac);
        zahtev.setPopunjavaZavod(popunjavaZavod);
        // TODO: metadata
        zahtevRepository.save(zahtev);
    }

    public void save(CreateZahtevDTO dto) throws Exception {
        var popunjavaPodnosilac = new PopunjavaPodnosilac(dto);
        var popunjavaZavod = new PopunjavaZavod();
        popunjavaZavod.setBrojPrijave(zahtevRepository.generateNextId());
        popunjavaZavod.setDatumPodnosenja(new Date());
        var opisDela = new Prilog.OpisDela();
        opisDela.setOpis(dto.getOpisDela());
        popunjavaZavod.setPrilog(opisDela);
        var zahtev = new Zahtev();
        zahtev.setPopunjavaPodnosilac(popunjavaPodnosilac);
        zahtev.setPopunjavaZavod(popunjavaZavod);
        // TODO: metadata
        zahtevRepository.save(zahtev);
    }

    public void save() throws Exception {
        var zahtev = marshalling.unmarshalFromFile("a1.xml");
        zahtevRepository.save(zahtev);
    }

    public InputStreamResource getAllMetadataAsJSON() throws Exception {
        return zahtevMetadataRepository.getAllAsJSON();
    }

    public InputStreamResource getAllMetadataAsRDF() throws Exception {
        return zahtevMetadataRepository.getAllAsRDF();
    }

    public List<Zahtev> getZahteviBezResenja() throws Exception {
        var reseniZahteviNames = resenjeRepository.getReferences();
        return zahtevRepository.getAllExcept(reseniZahteviNames);
    }
}
