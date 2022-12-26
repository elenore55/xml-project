package com.xml.autorsko_pravo.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPodnosilac", propOrder = {"adresa"})
public abstract class Podnosilac {

    @XmlElement(name = "Adresa", required = true)
    protected Adresa adresa;

    @Override
    public String toString() {
        return "- Podnosilac: \n\t" + adresa;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TFizicko_lice", propOrder = {"ime", "prezime", "drzavljanstvo"})
    public static class FizickoLice extends Podnosilac {

        @XmlElement(name = "Ime", required = true)
        String ime;

        @XmlElement(name = "Prezime", required = true)
        String prezime;

        @XmlElement(name = "Drzavljanstvo", required = true)
        String drzavljanstvo;

        @Override
        public String toString() {
            return super.toString() + "\n\t\t- Ime: " + ime + "\n\t\t- Prezime: " + prezime + "\n\t\t- Drzavljanstvo: " + drzavljanstvo;
        }
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TPravno_lice", propOrder = {"poslovnoIme"})
    public static class PravnoLice extends Podnosilac {

        @XmlElement(name = "Poslovno_ime", required = true)
        String poslovnoIme;

        @Override
        public String toString() {
            return super.toString() + "\n\t\t- Poslovno ime: " + poslovnoIme;
        }
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TPunomocnik", propOrder = {"ime", "prezime"})
    public static class Punomocnik extends Podnosilac {

        @XmlElement(name = "Ime", required = true)
        String ime;

        @XmlElement(name = "Prezime", required = true)
        String prezime;

        @Override
        public String toString() {
            return super.toString() + "\n\t\t- Ime: " + ime + "\n\t\t- Prezime: " + prezime;
        }
    }
}
