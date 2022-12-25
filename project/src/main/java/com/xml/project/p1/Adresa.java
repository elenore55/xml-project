package com.xml.project.p1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"ulica", "brojObjekta", "postanskiBroj", "mesto", "drzava"})
public class Adresa {

    @XmlElement(name = "Ulica", required = true)
    String ulica;

    @XmlElement(name = "Broj", required = true)
    int brojObjekta;

    @XmlElement(name = "Postanski_broj", required = true)
    int postanskiBroj;
    @XmlElement(name = "Mesto", required = true)
    String mesto;

    @XmlElement(name = "Drzava", required = true)
    String drzava;


    @Override
    public String toString() {
        return "\t- Adresa: " + mesto + " " + postanskiBroj + " " + ulica + " " + brojObjekta;
    }
}
