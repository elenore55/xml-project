package com.xml.users.service;

import com.xml.users.dto.RegistrationDTO;
import com.xml.users.model.Korisnik;
import com.xml.users.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class KorisnikService {

    PasswordEncoder passwordEncoder;
    KorisnikRepository korisnikRepository;

    @Autowired
    public KorisnikService(PasswordEncoder passwordEncoder, KorisnikRepository korisnikRepository) {
        this.passwordEncoder = passwordEncoder;
        this.korisnikRepository = korisnikRepository;
    }

    public void register(RegistrationDTO dto) throws Exception {
        var korisnik = new Korisnik();
        korisnik.setKorisnickoIme(dto.getKorisnickoIme());
        korisnik.setLozinka(passwordEncoder.encode(dto.getLozinka()));
        korisnik.setEmail(dto.getEmail());
        korisnik.setIme(dto.getIme());
        korisnik.setPrezime(dto.getPrezime());
        korisnik.setUloga(Korisnik.Uloga.GRADJANIN);
        korisnikRepository.register(korisnik);
    }
}
