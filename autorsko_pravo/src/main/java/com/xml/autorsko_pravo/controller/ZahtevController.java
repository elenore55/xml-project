package com.xml.autorsko_pravo.controller;

import com.xml.autorsko_pravo.model.Zahtev;
import com.xml.autorsko_pravo.repository.ZahtevRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
