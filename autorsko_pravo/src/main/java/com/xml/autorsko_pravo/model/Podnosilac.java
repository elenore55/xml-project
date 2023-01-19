package com.xml.autorsko_pravo.model;

import com.xml.autorsko_pravo.dto.PodnosilacDTO;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPodnosilac", propOrder = {"adresa", "telefon", "email"})
public abstract class Podnosilac {

    @XmlElement(name = "Adresa", required = true)
    protected Adresa adresa;

    @XmlElement(name = "Telefon", required = true)
    protected String telefon;

    @XmlElement(name = "E_mail", required = true)
    protected String email;

    @Override
    public String toString() {
        return "- Podnosilac: \n\t" + adresa;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TFizicko_lice", propOrder = {"ime", "prezime", "drzavljanstvo", "pseudonim"})
    public static class FizickoLice extends Podnosilac {

        public FizickoLice(PodnosilacDTO dto) {
            this.ime = dto.getIme();
            this.prezime = dto.getPrezime();
            this.drzavljanstvo = dto.getDrzavljanstvo();
            this.telefon = dto.getTelefon();
            this.email = dto.getEmail();
            if (!dto.getPseudonimAutora().equals(""))
                this.pseudonim = dto.getPseudonimAutora();
            this.adresa = new Adresa(dto.getAdresa());
        }

        @XmlElement(name = "Ime", required = true)
        String ime;

        @XmlElement(name = "Prezime", required = true)
        String prezime;

        @XmlElement(name = "Drzavljanstvo", required = true)
        String drzavljanstvo;

        @XmlElement(name = "Pseudonim")
        protected String pseudonim;

        @Override
        public String toString() {
            return super.toString() + "\n\t\t- Ime: " + ime + "\n\t\t- Prezime: " + prezime + "\n\t\t- Drzavljanstvo: " + drzavljanstvo;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TPravno_lice", propOrder = {"poslovnoIme"})
    public static class PravnoLice extends Podnosilac {

        public PravnoLice(PodnosilacDTO dto) {
            this.poslovnoIme = dto.getPoslovnoIme();
            this.telefon = dto.getTelefon();
            this.email = dto.getEmail();
            this.adresa = new Adresa(dto.getAdresa());
        }

        @XmlElement(name = "Poslovno_ime", required = true)
        String poslovnoIme;

        @Override
        public String toString() {
            return super.toString() + "\n\t\t- Poslovno ime: " + poslovnoIme;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TPunomocnik", propOrder = {"ime", "prezime"})
    public static class Punomocnik extends Podnosilac {

        public Punomocnik(PodnosilacDTO dto) {
            this.ime = dto.getIme();
            this.prezime = dto.getPrezime();
            this.telefon = dto.getTelefon();
            this.email = dto.getEmail();
            this.adresa = new Adresa(dto.getAdresa());
        }

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
