package com.xml.project.p1;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPunomocnik")
public abstract class Punomocnik extends Lice {

    public enum VrstaPunomocnika {
        PUNOMOCNIK_ZA_ZASTUPANJE, PUNOMOCNIK_ZA_PRIJEM_PISMENA, ZAJEDNICKI_PREDSTAVNIK
    }

    @XmlAttribute(name = "vrsta", required = true)
    VrstaPunomocnika vrsta;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TPunomocnik_fizicko_lice", propOrder = {"ime", "prezime"})
    public static class FizickoLice {

        @XmlElement(name = "Ime", required = true)
        String ime;

        @XmlElement(name = "Prezime", required = true)
        String prezime;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TPunomocnik_pravno_lice", propOrder = {"poslovnoIme"})
    public static class PravnoLice {

        @XmlElement(name = "Poslovno_ime", required = true)
        String poslovnoIme;
    }

}
