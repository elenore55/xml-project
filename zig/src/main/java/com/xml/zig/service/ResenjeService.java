package com.xml.zig.service;

import com.xml.zig.dto.CreateResenjeDTO;
import com.xml.zig.dto.TimePeriodDTO;
import com.xml.zig.model.Resenje;
import com.xml.zig.repository.ResenjeMetadataRepository;
import com.xml.zig.repository.ResenjeRepository;
import com.xml.zig.repository.ZahtevMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        return resenjeRepository.getAll();
    }

    public List<Resenje> search(String text, boolean matchCase) throws Exception {
        return resenjeRepository.search(text, matchCase);
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
        return resenjeMetadataRepository.advancedMetadataSearch(operators, statements);
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

    public List<String> getMetadataVariables() {
        return resenjeMetadataRepository.getMetadataVariables();
    }
}
