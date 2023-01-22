package com.xml.zig.model;

import com.xml.zig.dto.OsobaDTO;
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

    @Override
    public String toString() {
        return adresa + "\n\t\t- Telefon: " + telefon + "\n\t\t- E-mail: " + email + "\n\t\t- Faks: " + faks;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TFizicko_lice", propOrder = {"ime", "prezime"})
    public static class FizickoLice extends Lice {

        public FizickoLice(OsobaDTO dto) {
            this.ime = dto.getIme();
            this.prezime = dto.getPrezime();
            this.adresa = new Adresa(dto.getAdresa());
            this.telefon = dto.getTelefon();
            this.email = dto.getEmail();
            this.faks = dto.getFaks();
        }

        @XmlElement(name = "Ime", required = true)
        String ime;

        @XmlElement(name = "Prezime", required = true)
        String prezime;

        @Override
        public String toString() {
            return "\t- Ime: " + ime + "\n\t\t- Prezime: " + prezime + "\n\t" + super.toString();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TPravno_lice", propOrder = {"poslovnoIme"})
    public static class PravnoLice extends Lice {

        public PravnoLice(OsobaDTO dto) {
            this.poslovnoIme = dto.getPoslovnoIme();
            this.adresa = new Adresa(dto.getAdresa());
            this.telefon = dto.getTelefon();
            this.email = dto.getEmail();
            this.faks = dto.getFaks();
        }

        @XmlElement(name = "Poslovno_ime", required = true)
        String poslovnoIme;

        @Override
        public String toString() {
            return "\t- Poslovno ime: " + poslovnoIme + "\n\t" + super.toString();
        }
    }
}
