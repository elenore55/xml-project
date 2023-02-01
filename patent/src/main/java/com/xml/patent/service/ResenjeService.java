package com.xml.patent.service;

import com.xml.patent.dto.CreateResenjeDTO;
import com.xml.patent.dto.MetadataDTO;
import com.xml.patent.dto.TimePeriodDTO;
import com.xml.patent.model.Resenje;
import com.xml.patent.model.ResenjeComparator;
import com.xml.patent.repository.ResenjeMetadataRepository;
import com.xml.patent.repository.ResenjeRepository;
import com.xml.patent.repository.ZahtevMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResenjeService {

    MetadataSearchService metadataSearchService;
    ResenjeRepository resenjeRepository;
    ResenjeMetadataRepository resenjeMetadataRepository;
    ZahtevMetadataRepository zahtevMetadataRepository;
    PDFTransformer pdfTransformer;

    @Autowired
    public ResenjeService(MetadataSearchService metadataSearchService, ResenjeRepository resenjeRepository,
                          ResenjeMetadataRepository resenjeMetadataRepository, ZahtevMetadataRepository zahtevMetadataRepository,
                          PDFTransformer pdfTransformer) {
        this.metadataSearchService = metadataSearchService;
        this.resenjeRepository = resenjeRepository;
        this.resenjeMetadataRepository = resenjeMetadataRepository;
        this.zahtevMetadataRepository = zahtevMetadataRepository;
        this.pdfTransformer = pdfTransformer;
    }

    public void accept(CreateResenjeDTO dto) throws Exception {
        var resenje = new Resenje.OdobrenZahtev();
        resenje.setImeSluzbenika(dto.getImeSluzbenika());
        resenje.setPrezimeSluzbenika(dto.getPrezimeSluzbenika());
        resenje.setDatumResenja(new Date());
        resenje.setSifra(dto.getNazivDokumenta());
        resenje.setReferenca(dto.getNazivDokumenta());
        resenjeRepository.save(resenje);
        resenjeMetadataRepository.extract(resenje);
    }

    public void reject(CreateResenjeDTO.CreateOdbijenZahtevDTO dto) throws Exception {
        var resenje = new Resenje.OdbijenZahtev();
        resenje.setImeSluzbenika(dto.getImeSluzbenika());
        resenje.setPrezimeSluzbenika(dto.getPrezimeSluzbenika());
        resenje.setDatumResenja(new Date());
        resenje.setObrazlozenje(dto.getObrazlozenje());
        resenje.setReferenca(dto.getNazivDokumenta());
        resenjeRepository.save(resenje);
        resenjeMetadataRepository.extract(resenje);
    }

    public Resenje getOne(String name) throws Exception {
        return resenjeRepository.get(name);
    }

    public List<Resenje> getAll() throws Exception {
        var result = resenjeRepository.getAll();
        result.sort(new ResenjeComparator());
        return result;
    }

    public List<Resenje> search(String text, boolean matchCase) throws Exception {
        var result = resenjeRepository.search(text, matchCase);
        result.sort(new ResenjeComparator());
        return result;
    }

    public void extractMetadata(String name) throws Exception {
        resenjeMetadataRepository.extract(name);
    }

    public List<Resenje> simpleMetadataSearch(String name, String value) throws Exception {
        return resenjeMetadataRepository.simpleMetadataSearch(name, value);
    }

    public List<Resenje> advancedMetadataSearch(String rawInput) throws Exception {
        metadataSearchService.generate(rawInput);
        var operators = metadataSearchService.getOperators();
        var statements = metadataSearchService.getStatements();
        var result = resenjeMetadataRepository.advancedMetadataSearch(operators, statements);
        result.sort(new ResenjeComparator());
        return result.stream().distinct().collect(Collectors.toList());
    }

    public void generateReport(TimePeriodDTO dto) throws Exception {
        int odobreni = resenjeMetadataRepository.countOdobreniZahtevi(dto.getStart(), dto.getEnd());
        int odbijeni = resenjeMetadataRepository.countOdbijeniZahtevi(dto.getStart(), dto.getEnd());
        int svi = zahtevMetadataRepository.countZahtevi(dto.getStart(), dto.getEnd());
        pdfTransformer.generateReportPDF(dto, odobreni, odbijeni, svi);
    }

    public InputStreamResource getAllMetadataAsJSON() throws Exception {
        return resenjeMetadataRepository.getAllAsJSON();
    }

    public InputStreamResource getAllMetadataAsRDF() throws Exception {
        return resenjeMetadataRepository.getAllAsRDF();
    }

    public List<MetadataDTO> getMetadataVariables() {
        return resenjeMetadataRepository.getMetadataVariables();
    }
}
