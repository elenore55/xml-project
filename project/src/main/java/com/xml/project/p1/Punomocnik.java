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
        PUNOMOCNIK_ZA_ZASTUPANJE("Punomocnik za zastupanje"),
        PUNOMOCNIK_ZA_PRIJEM_PISMENA("Punomocnik za prijem pismena"),
        ZAJEDNICKI_PREDSTAVNIK("Zajednicki predstavnik");

        private final String name;

        VrstaPunomocnika(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @XmlAttribute(name = "vrsta", required = true)
    VrstaPunomocnika vrsta;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TPunomocnik_fizicko_lice", propOrder = {"ime", "prezime"})
    public static class FizickoLice extends Punomocnik {

        @XmlElement(name = "Ime", required = true)
        String ime;

        @XmlElement(name = "Prezime", required = true)
        String prezime;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TPunomocnik_pravno_lice", propOrder = {"poslovnoIme"})
    public static class PravnoLice extends Punomocnik {

        @XmlElement(name = "Poslovno_ime", required = true)
        String poslovnoIme;
    }

}
