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
@XmlType(name = "", propOrder = {"osnovnaTaksa", "klasa", "grafickoResenje", "ukupno"})
public class PlaceneTakse {

    @XmlElement(name = "Osnovna_taksa", required = true)
    double osnovnaTaksa;

    @XmlElement(name = "Klasa")
    KlasaTaksa klasa;

    @XmlElement(name = "Graficko_resenje")
    double grafickoResenje;

    @XmlElement(name = "Ukupno", required = true)
    double ukupno;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"naziv", "vrednost"})
    public static class KlasaTaksa {

        @XmlElement(name = "Naziv", required = true)
        String naziv;

        @XmlElement(name = "Vrednost", required = true)
        double vrednost;
    }

}
