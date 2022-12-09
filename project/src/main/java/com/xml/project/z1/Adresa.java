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
@XmlType(name = "", propOrder = {"ulica", "broj", "postanskiBroj", "mesto", "drzava"})
public class Adresa {

    @XmlElement(name = "Ulica", required = true)
    String ulica;

    @XmlElement(name = "Broj", required = true)
    int broj;

    @XmlElement(name = "Postanski_broj", required = true)
    int postanskiBroj;

    @XmlElement(name = "Mesto", required = true)
    String mesto;

    @XmlElement(name = "Drzava", required = true)
    String drzava;

    @Override
    public String toString() {
        return "\t- Adresa: " + ulica + " " + broj + " " + postanskiBroj + " " + mesto + " " + drzava;
    }
}
