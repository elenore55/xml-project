package com.xml.patent.model;

import com.xml.patent.dto.PronalazacDTO;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPronalazac", propOrder = {"brojFaksa", "ime", "prezime"})
public class Pronalazac extends Lice {

    public Pronalazac(PronalazacDTO dto) {
        this.ime = dto.getIme();
        this.prezime = dto.getPrezime();
        this.brojFaksa = dto.getFaks();
        this.ePosta = dto.getEmail();
        this.brojTelefona = dto.getTelefon();
        this.adresa = new Adresa(dto.getAdresa());
    }

    @XmlElement(name = "Broj_faksa", required = true)
    String brojFaksa;

    @XmlElement(name = "Ime", required = true)
    String ime;

    @XmlElement(name = "Prezime", required = true)
    String prezime;

    @Override
    public String toString() {
        return "\n\t- Pronalazac:\n" + super.toString() + "\n\t\t- Ime: " + ime + "\n\t\t- Prezime: " + prezime;
    }
}
