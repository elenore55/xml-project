package com.xml.patent.service;

import com.xml.patent.dto.CreateZahtevDTO;
import com.xml.patent.model.PopunjavaPodnosilac;
import com.xml.patent.model.PopunjavaZavod;
import com.xml.patent.model.Zahtev;
import com.xml.patent.model.ZahtevComparator;
import com.xml.patent.repository.Marshalling;
import com.xml.patent.repository.ResenjeRepository;
import com.xml.patent.repository.ZahtevMetadataRepository;
import com.xml.patent.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        var result = zahtevRepository.getAll();
        result.sort(new ZahtevComparator());
        return result;
    }

    public List<Zahtev> search(String text, boolean matchCase) throws Exception {
        var result = zahtevRepository.search(text, matchCase);
        result.sort(new ZahtevComparator());
        return result;
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
        var result = zahtevMetadataRepository.advancedMetadataSearch(operators, statements);
        result.sort(new ZahtevComparator());
        return result;
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

    public List<Zahtev> getZahteviBezResenja() throws Exception {
        var reseniZahteviNames = resenjeRepository.getReferences();
        var result = zahtevRepository.getAllExcept(reseniZahteviNames);
        result.sort(new ZahtevComparator());
        return result;
    }

    public List<Zahtev> searchZahteviBezResenja(String text, boolean matchCase) throws Exception {
        var reseniZahteviNames = resenjeRepository.getReferences();
        var zahtevi = zahtevRepository.search(text, matchCase);
        return getDifference(zahtevi, reseniZahteviNames);
    }

    public List<Zahtev> searchZahteviBezResenjaByMetadata(String rawInput) throws Exception {
        var reseniZahteviNames = resenjeRepository.getReferences();
        var zahtevi = advancedMetadataSearch(rawInput);
        return getDifference(zahtevi, reseniZahteviNames);
    }

    private List<Zahtev> getDifference(List<Zahtev> zahtevi, List<String> reseniZahteviNames) {
        var result = new ArrayList<Zahtev>();
        for (var z : zahtevi) {
            if (!reseniZahteviNames.contains(String.format("Zahtev%d.xml", z.getPopunjavaZavod().getBrojPrijave()))) {
                result.add(z);
            }
        }
        result.sort(new ZahtevComparator());
        return result;
    }

    public List<String> getMetadataVariables() {
        return zahtevMetadataRepository.getMetadataVariables();
    }

    public void save(CreateZahtevDTO dto) throws Exception {
        var popunjavaPodnosilac = new PopunjavaPodnosilac(dto);
        var popunjavaZavod = new PopunjavaZavod();
        popunjavaZavod.setDatumPodnosenja(new Date());
        popunjavaZavod.setDatumPrijema(new Date());
        popunjavaZavod.setBrojPrijave(zahtevRepository.generateNextId());

        var zahtev = new Zahtev();
        zahtev.setTipPrijave(Zahtev.TipPrijave.valueOf(dto.getVrstaPrijave()));
        zahtev.setPopunjavaPodnosilac(popunjavaPodnosilac);
        zahtev.setPopunjavaZavod(popunjavaZavod);
        zahtev.setPronalazacNeZeliBitiNaveden(dto.getPronalazac().isAnoniman());
        zahtevMetadataRepository.extract(zahtev);
        zahtevRepository.save(zahtev);
    }

    public List<Zahtev> getReferencingDocuments(int brojPrijave) throws Exception {
        var result = zahtevRepository.getReferencingDocuments(brojPrijave);
        result.sort(new ZahtevComparator());
        return result;
    }
}
