package com.xml.autorsko_pravo.service;

import com.xml.autorsko_pravo.dto.CreateResenjeDTO;
import com.xml.autorsko_pravo.dto.TimePeriodDTO;
import com.xml.autorsko_pravo.model.Resenje;
import com.xml.autorsko_pravo.model.Zahtev;
import com.xml.autorsko_pravo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ResenjeService {

    MetadataSearchService metadataSearchService;
    ResenjeRepository resenjeRepository;
    ZahtevRepository zahtevRepository;
    ResenjeMetadataRepository resenjeMetadataRepository;
    ZahtevMetadataRepository zahtevMetadataRepository;
    PDFTransformer pdfTransformer;
    EmailSender emailSender;

    @Autowired
    public ResenjeService(MetadataSearchService metadataSearchService, ResenjeRepository resenjeRepository, ZahtevRepository zahtevRepository,
                          ResenjeMetadataRepository resenjeMetadataRepository, ZahtevMetadataRepository zahtevMetadataRepository,
                          PDFTransformer pdfTransformer, EmailSender emailSender) {
        this.metadataSearchService = metadataSearchService;
        this.resenjeRepository = resenjeRepository;
        this.zahtevRepository = zahtevRepository;
        this.resenjeMetadataRepository = resenjeMetadataRepository;
        this.zahtevMetadataRepository = zahtevMetadataRepository;
        this.pdfTransformer = pdfTransformer;
        this.emailSender = emailSender;
    }

    public void accept(CreateResenjeDTO dto) throws Exception {
        var resenje = new Resenje.OdobrenZahtev();
        resenje.setImeSluzbenika(dto.getImeSluzbenika());
        resenje.setPrezimeSluzbenika(dto.getPrezimeSluzbenika());
        resenje.setDatumResenja(new Date());
        resenje.setSifra(dto.getNazivDokumenta());
        resenje.setReferenca(dto.getNazivDokumenta());
        resenjeRepository.save(resenje);
        notifyPodnosilac(resenje);
    }

    public void reject(CreateResenjeDTO.CreateOdbijenZahtevDTO dto) throws Exception {
        var resenje = new Resenje.OdbijenZahtev();
        resenje.setImeSluzbenika(dto.getImeSluzbenika());
        resenje.setPrezimeSluzbenika(dto.getPrezimeSluzbenika());
        resenje.setDatumResenja(new Date());
        resenje.setObrazlozenje(dto.getObrazlozenje());
        resenje.setReferenca(dto.getNazivDokumenta());
        resenjeRepository.save(resenje);
        notifyPodnosilac(resenje);
    }

    Runnable generatePdfTask(Resenje.OdobrenZahtev resenje, Zahtev zahtev) {
        return () -> {
            try {
                pdfTransformer.generateResenjePDF(resenje, zahtev);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    Runnable generatePdfTask(Resenje.OdbijenZahtev resenje, Zahtev zahtev) {
        return () -> {
            try {
                pdfTransformer.generateResenjePDF(resenje, zahtev);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    Runnable sendEmailTask(Zahtev zahtev, String status) {
        return () -> emailSender.send(zahtev.getPopunjavaPodnosilac().getPodnosilac().getEmail(), new EmailMessage(status));
    }
    public void notifyPodnosilac(Resenje.OdobrenZahtev resenje) throws Exception {
        var zahtev = zahtevRepository.get(resenje.getReferenca() + ".xml");
        CompletableFuture.runAsync(generatePdfTask(resenje, zahtev))
                .thenRunAsync(sendEmailTask(zahtev, "ODOBREN"));
    }

    public void notifyPodnosilac(Resenje.OdbijenZahtev resenje) throws Exception {
        var zahtev = zahtevRepository.get(resenje.getReferenca() + ".xml");
        CompletableFuture.runAsync(generatePdfTask(resenje, zahtev))
                .thenRunAsync(sendEmailTask(zahtev, "ODBIJEN"));
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
        pdfTransformer.generateIzvestajPDF(dto, odobreni, odbijeni, svi);
    }

    public InputStreamResource getAllMetadataAsJSON() throws Exception {
        return resenjeMetadataRepository.getAllAsJSON();
    }

    public InputStreamResource getAllMetadataAsRDF() throws Exception {
        return resenjeMetadataRepository.getAllAsRDF();
    }
}
