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
@XmlType(name = "", propOrder = {"mesto", "postanskiBroj", "ulica", "brojObjekta"})
public class Adresa {

    @XmlElement(name = "Mesto", required = true)
    String mesto;

    @XmlElement(name = "Postanski_broj", required = true)
    int postanskiBroj;

    @XmlElement(name = "Ulica", required = true)
    String ulica;

    @XmlElement(name = "Broj", required = true)
    int brojObjekta;

    @Override
    public String toString() {
        return "\t- Adresa: " + mesto + " " + postanskiBroj + " " + ulica + " " + brojObjekta;
    }
}
