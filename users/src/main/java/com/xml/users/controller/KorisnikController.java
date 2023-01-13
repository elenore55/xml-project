package com.xml.users.controller;

import com.xml.users.dto.RegistrationDTO;
import com.xml.users.service.KorisnikService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "korisnici/")
public class KorisnikController {

    KorisnikService korisnikService;

    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @PostMapping(value = "register", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Void> register(@RequestBody RegistrationDTO dto) {
        try {
            korisnikService.register(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
