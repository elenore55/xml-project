package com.xml.zig.controller;

import com.xml.zig.dto.CreateResenjeDTO;
import com.xml.zig.dto.TimePeriodDTO;
import com.xml.zig.model.Resenje;
import com.xml.zig.service.PDFTransformer;
import com.xml.zig.service.ResenjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@RestController
@RequestMapping(value = "zig/resenje/")
public class ResenjeController {

    ResenjeService resenjeService;

    @Autowired
    public ResenjeController(ResenjeService resenjeService) {
        this.resenjeService = resenjeService;
    }

    @PostMapping(value = "accept", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Void> accept(@RequestBody CreateResenjeDTO dto) {
        try {
            resenjeService.accept(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "reject", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Void> reject(@RequestBody CreateResenjeDTO.CreateOdbijenZahtevDTO dto) {
        try {
            resenjeService.reject(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "one/{name}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Resenje> getOne(@PathVariable String name) throws Exception {
        var resenje = resenjeService.getOne(name);
        if (resenje == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(resenje, HttpStatus.OK);
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Resenje>> getAll() throws Exception {
        return new ResponseEntity<>(resenjeService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "search/{text}/{matchCase}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Resenje>> search(@PathVariable String text, @PathVariable boolean matchCase) throws Exception {
        return new ResponseEntity<>(resenjeService.search(text, matchCase), HttpStatus.OK);
    }

    @GetMapping(value = "metadata/rdf/{name}")
    public ResponseEntity<Void> extractMetadata(@PathVariable String name) throws Exception {
        resenjeService.extractMetadata(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "metadata/simpleSearch/{name}/{value}")
    public ResponseEntity<List<Resenje>> simpleMetadataSearch(@PathVariable String name, @PathVariable String value) throws Exception {
        return new ResponseEntity<>(resenjeService.simpleMetadataSearch(name, value), HttpStatus.OK);
    }

    @GetMapping(value = "metadata/advancedSearch/{rawInput}")
    public ResponseEntity<List<Resenje>> advancedMetadataSearch(@PathVariable String rawInput) throws Exception {
        return new ResponseEntity<>(resenjeService.advancedMetadataSearch(rawInput), HttpStatus.OK);
    }

    @PostMapping(value = "report", produces = MediaType.APPLICATION_PDF_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<InputStreamResource> generateReport(@RequestBody TimePeriodDTO dto) {
        try {
            resenjeService.generateReport(dto);
            var file = new File(PDFTransformer.IZVESTAJ_PDF_FILE);
            var resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(file.length())
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "metadata/all/json")
    public ResponseEntity<InputStreamResource> getAllMetadataAsJSON() throws Exception {
        var resource = resenjeService.getAllMetadataAsJSON();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=metadata.json")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping(value = "metadata/all/rdf")
    public ResponseEntity<InputStreamResource> getAllMetadataAsRDF() throws Exception {
        var resource = resenjeService.getAllMetadataAsRDF();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=metadata.rdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping(value = "metadata/vars", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<String>> getMetadataVariables() {
        return new ResponseEntity<>(resenjeService.getMetadataVariables(), HttpStatus.OK);
    }
}
