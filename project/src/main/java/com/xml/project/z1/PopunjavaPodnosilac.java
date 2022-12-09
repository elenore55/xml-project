package com.xml.project.z1;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"podnosiociPrijave", "punomocnik", "zajednickiPredstavnik", "podaciOZigu",
        "pravoPrvenstva", "placeneTakse"})
public class PopunjavaPodnosilac {

    @XmlElementWrapper(name = "Podnosioci_prijave", required = true)
    @XmlElement(name = "Podnosilac_prijave", required = true)
    List<Lice> podnosiociPrijave;

    @XmlElement(name = "Punomocnik", required = true)
    Lice punomocnik;

    @XmlElement(name = "Zajednicki_predstavnik")
    Lice zajednickiPredstavnik;

    @XmlElement(name = "Podaci_o_zigu", required = true)
    PodaciOZigu podaciOZigu;

    @XmlElement(name = "Pravo_prvenstva", required = true)
    String pravoPrvenstva;

    @XmlElement(name = "Placene_takse", required = true)
    PlaceneTakse placeneTakse;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("- Popunjava podnosilac:");
        builder.append("\n\t- Podnosioci prijave");
        for (var lice : podnosiociPrijave)
            builder.append("\n\t").append(lice);
        builder.append("\n\t- Punomocnik:").append("\n\t").append(punomocnik);
        if (zajednickiPredstavnik != null)
            builder.append("\n\t- Zajednicki predstavnik:").append("\n\t").append(zajednickiPredstavnik);
        builder.append("\n").append(podaciOZigu);
        builder.append("\n\t- Pravo prvenstva: ").append(pravoPrvenstva);
        builder.append("\n").append(placeneTakse);
        return builder.toString();
    }
}
