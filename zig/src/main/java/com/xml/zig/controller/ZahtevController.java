package com.xml.zig.controller;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.xml.zig.dto.CreateZahtevDTO;
import com.xml.zig.model.Zahtev;
import com.xml.zig.service.HTMLTransformer;
import com.xml.zig.service.PDFTransformer;
import com.xml.zig.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
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
    public ResponseEntity<InputStreamResource> getHtml(@PathVariable String name) throws Exception {
        htmlTransformer.generateHtml(name);
        var file = new File(HTMLTransformer.HTML_FILE);
        var resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(MediaType.TEXT_HTML)
                .contentLength(file.length())
                .body(resource);
    }

    @GetMapping(value = "zahtev/htmlString/{name}", produces = "text/html;charset=UTF-8")
    public ResponseEntity<String> getHtmlString(@PathVariable String name) throws Exception {
        htmlTransformer.generateHtml(name);
        String content = Files.readString(Paths.get(HTMLTransformer.HTML_FILE));
        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    @GetMapping(value = "zahtev/pdf/{name}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getPdf(@PathVariable String name) throws Exception {
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

    @GetMapping(value = "metadata/simpleSearch/{name}/{value}")
    public ResponseEntity<List<Zahtev>> simpleMetadataSearch(@PathVariable String name, @PathVariable String value) throws Exception {
        return new ResponseEntity<>(zahtevService.simpleMetadataSearch(name, value), HttpStatus.OK);
    }

    @GetMapping(value = "metadata/advancedSearch/{rawInput}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Zahtev>> advancedMetadataSearch(@PathVariable String rawInput) throws Exception {
        return new ResponseEntity<>(zahtevService.advancedMetadataSearch(rawInput), HttpStatus.OK);
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

    @PostMapping(value = "zahtev/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> save(
            @RequestParam("izgledZnaka") MultipartFile izgledZnaka, @RequestParam("primerakZnaka") MultipartFile primerakZnaka,
            @RequestParam("spisakRobeIUsluga") MultipartFile spisakRobeIUsluga, @RequestParam(value = "punomocje", required = false) MultipartFile punomocje,
            @RequestParam("dokazOPravuPrvenstva") MultipartFile dokazOPravuPrvenstva, @RequestParam("dokazOUplatiTakse") MultipartFile dokazOUplatiTakse,
            @RequestParam(value = "opstiAkt", required = false) MultipartFile opstiAkt, @RequestParam("dto") String dto) throws Exception {
        var mapper = new XmlMapper();
        var zahtev = mapper.readValue(dto, CreateZahtevDTO.class);
        zahtevService.save(zahtev, new HashMap<>(
        ){{
            put("izgledZnaka", izgledZnaka);
            put("primerakZnaka", primerakZnaka);
            put("spisakRobeIUsluga", spisakRobeIUsluga);
            put("punomocje", punomocje);
            put("opstiAkt", opstiAkt);
            put("dokazOPravuPrvenstva", dokazOPravuPrvenstva);
            put("dokazOUplatiTakse", dokazOUplatiTakse);
        }});
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "metadata/vars", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<String>> getMetadataVariables() {
        return new ResponseEntity<>(zahtevService.getMetadataVariables(), HttpStatus.OK);
    }
}
