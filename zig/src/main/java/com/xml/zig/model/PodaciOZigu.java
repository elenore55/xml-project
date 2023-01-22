package com.xml.zig.model;

import com.xml.zig.dto.ZigDTO;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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

    public PodaciOZigu(ZigDTO dto) {
        this.tipZiga = TipZiga.valueOf(dto.getTipZiga());
        this.vrstaZnaka = dto.getVrstaZnaka();
        this.boje = new ArrayList<>();
        boje.addAll(dto.getBojeList());
        this.transliteracija = dto.getTransliteracija();
        this.prevod = dto.getPrevod();
        this.opis = dto.getOpis();
        this.nicanskaKlasifikacija = new ArrayList<>();
        nicanskaKlasifikacija.addAll(dto.getNicanskaKlasifikacijaList());
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\t- Podaci o zigu: ");
        builder.append("\n\t\t- Tip ziga: ").append(tipZiga);
        builder.append("\n\t\t- Vrsta znaka: ").append(vrstaZnaka);
        builder.append("\n\t\t- Boje: ");
        for (var boja : boje)
            builder.append("\n\t\t\t- ").append(boja);
        if (transliteracija != null && !transliteracija.equals(""))
            builder.append("\n\t\t- Transliteracija: ").append(transliteracija);
        if (prevod != null && !prevod.equals(""))
            builder.append("\n\t\t- Prevod: ").append(prevod);
        builder.append("\n\t\t- Opis: ").append(opis);
        builder.append("\n\t\t- Izgled: ").append(izgled);
        builder.append("\n\t\t- Nicanska klasifikacija: ");
        for (var klasa : nicanskaKlasifikacija)
            builder.append("\n\t\t\t- ").append(klasa);
        return builder.toString();
    }
}
