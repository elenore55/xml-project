package com.xml.autorsko_pravo.model;

import com.xml.autorsko_pravo.dto.AutorDTO;
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
@XmlType(name = "TAutor", propOrder = {"ime", "prezime", "pseudonim"})
public abstract class Autor {

    @XmlElement(name = "Ime", required = true)
    protected String ime;

    @XmlElement(name = "Prezime", required = true)
    protected String prezime;

    @XmlElement(name = "Pseudonim")
    protected String pseudonim;

    @Override
    public String toString() {
        return "\n\t\t- Ime: " + ime + "\n\t\t- Prezime: " + prezime;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TZiv_autor", propOrder = {"adresa", "drzavljanstvo"})
    public static class ZivAutor extends Autor {

        public ZivAutor(AutorDTO dto) {
            this.ime = dto.getIme();
            this.prezime = dto.getPrezime();
            this.adresa = new Adresa(dto.getAdresa());
            this.drzavljanstvo = dto.getDrzavljanstvo();
            if (dto.getPseudonim() != null && !dto.getPseudonim().equals(""))
                this.pseudonim = dto.getPseudonim();
        }

        @XmlElement(name = "Adresa", required = true)
        Adresa adresa;

        @XmlElement(name = "Drzavljanstvo", required = true)
        String drzavljanstvo;

        @Override
        public String toString() {
            return super.toString() + "\n\t" + adresa + "\n\t\t- Drzavljanstvo: " + drzavljanstvo + "\n";
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TPokojni_autor", propOrder = {"godinaSmrti"})
    public static class PokojniAutor extends Autor {

        public PokojniAutor(AutorDTO dto) {
            this.ime = dto.getIme();
            this.prezime = dto.getPrezime();
            this.godinaSmrti = dto.getGodinaSmrti();
            if (dto.getPseudonim() != null && !dto.getPseudonim().equals(""))
                this.pseudonim = dto.getPseudonim();
        }

        @XmlElement(name = "Godina_smrti", required = true)
        int godinaSmrti;

        @Override
        public String toString() {
            return super.toString() + "\n\t\t- Godina smrti: " + godinaSmrti + "\n";
        }
    }
}
