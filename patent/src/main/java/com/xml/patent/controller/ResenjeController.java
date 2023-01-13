package com.xml.patent.controller;

import com.xml.patent.dto.CreateResenjeDTO;
import com.xml.patent.model.Resenje;
import com.xml.patent.service.ResenjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "patent/resenje/")
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
}
