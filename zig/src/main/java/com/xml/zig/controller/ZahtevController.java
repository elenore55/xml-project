package com.xml.zig.controller;

import com.xml.zig.model.Zahtev;
import com.xml.zig.service.HTMLTransformer;
import com.xml.zig.service.PDFTransformer;
import com.xml.zig.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "zig/")
public class ZahtevController {

    ZahtevService zahtevService;
    HTMLTransformer htmlTransformer;
    PDFTransformer pdfTransformer;

    @Autowired
    public ZahtevController(ZahtevService zahtevService, HTMLTransformer htmlTransformer, PDFTransformer pdfTransformer) {
        this.zahtevService = zahtevService;
        this.htmlTransformer = htmlTransformer;
        this.pdfTransformer = pdfTransformer;
    }

    @GetMapping(value = "zahtev/{name}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Zahtev> getOne(@PathVariable String name) throws Exception {
        var zahtev = zahtevService.getOne(name);
        if (zahtev == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(zahtev, HttpStatus.OK);
    }

    @GetMapping(value = "zahtev/all", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Zahtev>> getAll() throws Exception {
        return new ResponseEntity<>(zahtevService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "zahtev/html/{name}")
    public ModelAndView getHtml(@PathVariable String name) {
        htmlTransformer.generateHtml(name);
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("z1.html");
        return modelAndView;
    }

    @GetMapping(value = "zahtev/pdf/{name}")
    public ResponseEntity<byte[]> getPdf(@PathVariable String name) throws IOException {
        pdfTransformer.generatePDF(name);
        byte[] content = Files.readAllBytes(new File(PDFTransformer.PDF_FILE).toPath());
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "z1.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }

    @GetMapping(value = "zahtev/search/{text}/{matchCase}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Zahtev>> search(@PathVariable String text, @PathVariable boolean matchCase) throws Exception {
        return new ResponseEntity<>(zahtevService.search(text, matchCase), HttpStatus.OK);
    }

    @GetMapping(value = "metadata/rdf/{name}")
    public ResponseEntity<Void> extractMetadata(@PathVariable String name) throws Exception {
        zahtevService.extractMetadata(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
