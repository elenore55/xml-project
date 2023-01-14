package com.xml.autorsko_pravo.controller;

import com.xml.autorsko_pravo.dto.CreateResenjeDTO;
import com.xml.autorsko_pravo.model.Resenje;
import com.xml.autorsko_pravo.service.ResenjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "autorsko_pravo/resenje/")
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

    @GetMapping(value = "metadata/simpleSearch/{name}/{value}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Resenje>> simpleMetadataSearch(@PathVariable String name, @PathVariable String value) throws Exception {
        return new ResponseEntity<>(resenjeService.simpleMetadataSearch(name, value), HttpStatus.OK);
    }

    @GetMapping(value = "metadata/advancedSearch/{rawInput}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Resenje>> advancedMetadataSearch(@PathVariable String rawInput) throws Exception {
        return new ResponseEntity<>(resenjeService.advancedMetadataSearch(rawInput), HttpStatus.OK);
    }
}
