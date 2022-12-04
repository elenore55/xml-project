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
}
