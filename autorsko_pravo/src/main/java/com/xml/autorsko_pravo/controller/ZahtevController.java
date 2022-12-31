package com.xml.autorsko_pravo.controller;

import com.xml.autorsko_pravo.model.Zahtev;
import com.xml.autorsko_pravo.repository.ZahtevRepository;
import com.xml.autorsko_pravo.service.HTMLTransformer;
import com.xml.autorsko_pravo.service.PDFTransformer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping(value = "autorsko-pravo/")
public class ZahtevController {

    @GetMapping(value = "zahtev/{name}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Zahtev> getOne(@PathVariable String name) throws Exception {
        var repository = new ZahtevRepository();
        var zahtev = repository.get(name);
        if (zahtev == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(zahtev, HttpStatus.OK);
    }

    @GetMapping(value = "zahtev/all", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Zahtev>> getAll() throws Exception {
        var repository = new ZahtevRepository();
        return new ResponseEntity<>(repository.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "zahtev/html/{name}")
    public ModelAndView getHtml(@PathVariable String name) {
        var transform = new HTMLTransformer();
        transform.generateHtml(name);
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("a1.html");
        return modelAndView;
    }

    @GetMapping(value = "zahtev/pdf/{name}")
    public ResponseEntity<byte[]> getPdf(@PathVariable String name) throws IOException {
        var transform = new PDFTransformer();
        transform.generatePDF(name);
        byte[] content = Files.readAllBytes(new File(PDFTransformer.PDF_FILE).toPath());
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "a1.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }
}
