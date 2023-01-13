package com.xml.users.model;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public Korisnik createKorisnik() {
        return new Korisnik();
    }
}
