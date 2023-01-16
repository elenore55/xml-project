package com.xml.users.controller;

import com.xml.users.dto.KorisnikDTO;
import com.xml.users.dto.LoginDTO;
import com.xml.users.dto.RegistrationDTO;
import com.xml.users.service.KorisnikService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "login", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<KorisnikDTO> login(@RequestBody LoginDTO dto) {
        try {
            var korisnik = korisnikService.login(dto);
            return new ResponseEntity<>(korisnik, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
