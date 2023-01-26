package com.xml.patent.controller;

import com.xml.patent.dto.CreateZahtevDTO;
import com.xml.patent.model.Zahtev;
import com.xml.patent.service.HTMLTransformer;
import com.xml.patent.service.PDFTransformer;
import com.xml.patent.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "patent/")
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
    public ResponseEntity<InputStreamResource> getHtml(@PathVariable String name) throws FileNotFoundException {
        htmlTransformer.generateHtml(name);
        var file = new File(HTMLTransformer.HTML_FILE);
        var resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(MediaType.TEXT_HTML)
                .contentLength(file.length())
                .body(resource);
    }

    @GetMapping(value = "zahtev/htmlString/{name}")
    public ResponseEntity<String> getHtmlString(@PathVariable String name) throws IOException {
        htmlTransformer.generateHtml(name);
        String content = Files.readString(Paths.get(HTMLTransformer.HTML_FILE), StandardCharsets.UTF_8);
        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    @GetMapping(value = "zahtev/pdf/{name}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getPdf(@PathVariable String name) throws IOException {
        pdfTransformer.generatePDF(name);
        var file = new File(PDFTransformer.PDF_FILE);
        var resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(file.length())
                .body(resource);
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

    @GetMapping(value = "metadata/simpleSearch/{name}/{value}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Zahtev>> simpleMetadataSearch(@PathVariable String name, @PathVariable String value) throws Exception {
        return new ResponseEntity<>(zahtevService.simpleMetadataSearch(name, value), HttpStatus.OK);
    }

    @GetMapping(value = "metadata/advancedSearch/{rawInput}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Zahtev>> advancedMetadataSearch(@PathVariable String rawInput) throws Exception {
        return new ResponseEntity<>(zahtevService.advancedMetadataSearch(rawInput), HttpStatus.OK);
    }

    @GetMapping(value = "zahtev/save")
    public ResponseEntity<Void> save() throws Exception {
        zahtevService.save();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "metadata/all/json")
    public ResponseEntity<InputStreamResource> getAllMetadataAsJSON() throws Exception {
        var resource = zahtevService.getAllMetadataAsJSON();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=metadata.json")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping(value = "metadata/all/rdf")
    public ResponseEntity<InputStreamResource> getAllMetadataAsRDF() throws Exception {
        var resource = zahtevService.getAllMetadataAsRDF();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=metadata.rdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping(value = "zahtev/bezResenja", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Zahtev>> getZahteviBezResenja() throws Exception {
        return new ResponseEntity<>(zahtevService.getZahteviBezResenja(), HttpStatus.OK);
    }

    @GetMapping(value = "zahtev/bezResenja/search/{text}/{matchCase}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Zahtev>> searchZahteviBezResenja(@PathVariable String text, @PathVariable boolean matchCase) throws Exception {
        return new ResponseEntity<>(zahtevService.searchZahteviBezResenja(text, matchCase), HttpStatus.OK);
    }

    @GetMapping(value = "metadata/bezResenja/advancedSearch/{rawInput}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Zahtev>> advancedMetadataSearchBezResenja(@PathVariable String rawInput) throws Exception {
        return new ResponseEntity<>(zahtevService.searchZahteviBezResenjaByMetadata(rawInput), HttpStatus.OK);
    }

    @GetMapping(value = "metadata/vars", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<String>> getMetadataVariables() {
        return new ResponseEntity<>(zahtevService.getMetadataVariables(), HttpStatus.OK);
    }

    @PostMapping(value = "zahtev/save", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Void> save(@RequestBody CreateZahtevDTO dto) throws Exception {
        zahtevService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "zahtev/getReferencing/{brojPrijave}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Zahtev>> getReferencingDocuments(@PathVariable int brojPrijave) throws Exception {
        return new ResponseEntity<>(zahtevService.getReferencingDocuments(brojPrijave), HttpStatus.OK);
    }
}
