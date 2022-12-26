package com.xml.patent.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPronalazac", propOrder = {"brojFaksa", "ime", "prezime"})
public class Pronalazac extends Lice {

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
