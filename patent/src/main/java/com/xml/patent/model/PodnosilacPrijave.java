package com.xml.patent.model;

import com.xml.patent.dto.PodnosilacDTO;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPodnosilac_prijave", propOrder = {"brojFaksa"})
public abstract class PodnosilacPrijave extends Lice {

    @XmlAttribute(name = "pronalazac", required = true)
    protected boolean pronalazac;

    @XmlElement(name = "Broj_faksa", required = true)
    protected String brojFaksa;

    @Override
    public String toString() {
        String val = "\n\t- Podnosilac prijave:\n" + super.toString() + "\n\t\t- Broj faksa: " + brojFaksa;
        if (pronalazac)
            val += "\n\t\t- Podnosilac je pronalazac";
        return val;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TPodnosilac_fizicko_lice", propOrder = {"ime", "prezime", "drzavljanstvo"})
    public static class FizickoLice extends PodnosilacPrijave {

        public FizickoLice(PodnosilacDTO dto) {
            this.ime = dto.getIme();
            this.prezime = dto.getPrezime();
            this.ePosta = dto.getEmail();
            this.drzavljanstvo = dto.getDrzavljanstvo();
            this.brojTelefona = dto.getTelefon();
            this.brojFaksa = dto.getFaks();
            this.pronalazac = dto.isPronalazac();
            this.adresa = new Adresa(dto.getAdresa());
        }

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
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TPodnosilac_pravno_lice", propOrder = {"poslovnoIme"})
    public static class PravnoLice extends PodnosilacPrijave {

        public PravnoLice(PodnosilacDTO dto) {
            this.poslovnoIme = dto.getPoslovnoIme();
            this.ePosta = dto.getEmail();
            this.brojTelefona = dto.getTelefon();
            this.brojFaksa = dto.getFaks();
            this.pronalazac = dto.isPronalazac();
            this.adresa = new Adresa(dto.getAdresa());
        }

        @XmlElement(name = "Poslovno_ime", required = true)
        String poslovnoIme;

        @Override
        public String toString() {
            return super.toString() + "\n\t\t- Poslovno ime: " + poslovnoIme;
        }
    }
}
