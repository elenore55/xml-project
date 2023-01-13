package com.xml.users.dto;

import com.xml.users.model.Korisnik;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KorisnikDTO {
    String korisnickoIme;
    String email;
    String ime;
    String prezime;
    String uloga;

    public KorisnikDTO(Korisnik korisnik) {
        this.korisnickoIme = korisnik.getKorisnickoIme();
        this.email = korisnik.getEmail();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.uloga = korisnik.getUloga().toString();
    }
}
