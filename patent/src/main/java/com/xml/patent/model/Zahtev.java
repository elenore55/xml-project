package com.xml.patent.model;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Zahtev")
@XmlType(name = "", propOrder = {"popunjavaZavod", "popunjavaPodnosilac"})
public class Zahtev {

    public enum TipPrijave {
        IZDVOJENA("Izdvojena"), DOPUNSKA("Dopunska");

        private final String name;

        TipPrijave(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @XmlAttribute(name = "tip_prijave", required = true)
    TipPrijave tipPrijave;

    @XmlAttribute(name = "pronalazac_ne_zeli_biti_naveden")
    boolean pronalazacNeZeliBitiNaveden;

    @XmlElement(name = "Popunjava_zavod", required = true)
    PopunjavaZavod popunjavaZavod;

    @XmlElement(name = "Popunjava_podnosilac", required = true)
    PopunjavaPodnosilac popunjavaPodnosilac;

    @Override
    public String toString() {
        return "\nZAHTEV P1 (" + tipPrijave + " prijava)\n" + popunjavaPodnosilac + "\n" + popunjavaZavod + "\n";
    }
}
