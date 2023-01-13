package com.xml.users.model;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Korisnik")
@XmlType(name = "", propOrder = {"korisnickoIme", "lozinka", "email", "ime", "prezime"})
public class Korisnik {

    public enum Uloga {
        SLUZBENIK, GRADJANIN;
    }

    @XmlAttribute(name = "Uloga", required = true)
    Uloga uloga;

    @XmlElement(name = "Korisnicko_ime", required = true)
    String korisnickoIme;

    @XmlElement(name = "Lozinka", required = true)
    String lozinka;

    @XmlElement(name = "Email", required = true)
    String email;

    @XmlElement(name = "Ime", required = true)
    String ime;

    @XmlElement(name = "Prezime", required = true)
    String prezime;
}
