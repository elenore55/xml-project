package com.xml.project.z1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TLice", propOrder = {"adresa", "telefon", "email", "faks"})
public abstract class Lice {

    @XmlElement(name = "Adresa", required = true)
    Adresa adresa;

    @XmlElement(name = "Telefon", required = true)
    String telefon;

    @XmlElement(name = "E_mail", required = true)
    String email;

    @XmlElement(name = "Faks", required = true)
    String faks;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TFizicko_lice", propOrder = {"ime", "prezime"})
    public static class FizickoLice extends Lice {

        @XmlElement(name = "Ime", required = true)
        String ime;

        @XmlElement(name = "Prezime", required = true)
        String prezime;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TPravno_lice", propOrder = {"poslovnoIme"})
    public static class PravnoLice extends Lice {

        @XmlElement(name = "Poslovno_ime", required = true)
        String poslovnoIme;
    }
}
