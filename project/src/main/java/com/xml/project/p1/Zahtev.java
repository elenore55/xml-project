package com.xml.project.p1;

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
        IZDVOJENA("izdvojena"), DOPUNSKA("dopunska");

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

    @XmlElement(name = "Popunjava_zavod", required = true)
    PopunjavaZavod popunjavaZavod;

    @XmlElement(name = "Popunjava_podnosilac", required = true)
    PopunjavaPodnosilac popunjavaPodnosilac;

    @Override
    public String toString() {
        return "\nZAHTEV P1\n" + popunjavaPodnosilac + "\n" + popunjavaZavod + "\n";
    }
}
