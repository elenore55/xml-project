package com.xml.project.z1;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"tipZiga", "vrstaZnaka", "boje", "transliteracija", "prevod",
        "opis", "izgled", "nicanskaKlasifikacija"})
public class PodaciOZigu {

    public enum TipZiga {
        INDIVIDUALNI_ZIG("individualni zig"),
        KOLEKTIVNI_ZIG("kolektivni zig"),
        ZIG_GARANCIJE("zig garancije");

        private final String name;

        TipZiga(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @XmlElement(name = "Tip_ziga", required = true)
    TipZiga tipZiga;

    @XmlElement(name = "Vrsta_znaka", required = true)
    String vrstaZnaka;

    @XmlElementWrapper(name = "Boje", required = true)
    @XmlElement(name = "Boja", required = true)
    List<String> boje;

    @XmlElement(name = "Transliteracija")
    String transliteracija;

    @XmlElement(name = "Prevod")
    String prevod;

    @XmlElement(name = "Opis", required = true)
    String opis;

    @XmlElement(name = "Izgled", required = true)
    String izgled;

    @XmlElementWrapper(name = "Nicanska_klasifikacija", required = true)
    @XmlElement(name = "Klasa", required = true)
    List<Integer> nicanskaKlasifikacija;
}
