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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\t- Placene takse:");
        builder.append("\n\t\t- Osnovna taksa: ").append(osnovnaTaksa).append(" RSD");
        if (klasa != null)
            builder.append("\n\t").append(klasa);
        if (grafickoResenje != 0)
            builder.append("\n\t\t- Graficko resenje: ").append(grafickoResenje).append(" RSD");
        builder.append("\n\t\t- Ukupno: ").append(ukupno).append(" RSD");
        return builder.toString();
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"naziv", "vrednost"})
    public static class KlasaTaksa {

        @XmlElement(name = "Naziv", required = true)
        String naziv;

        @XmlElement(name = "Vrednost", required = true)
        double vrednost;

        @Override
        public String toString() {
            return "\t- " + naziv + ": " + vrednost + " RSD";
        }
    }

}
