package com.xml.project.p1;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"nazivPronalaskaSR", "nazivPronalaskaEN", "podnosilacPrijave", "pronalazac", "punomocnik",
        "podaciODostavljanju", "prvobitnaPrijava", "zahtevZaPriznanjePrvenstva"})
public class PopunjavaPodnosilac {

    @XmlElement(name = "Naziv_pronalaska", required = true)
    NazivPronalaska nazivPronalaskaSR;

    @XmlElement(name = "Naziv_pronalaska", required = true)
    NazivPronalaska nazivPronalaskaEN;

    @XmlElement(name = "Podnosilac_prijave", required = true)
    PodnosilacPrijave podnosilacPrijave;

    @XmlElement(name = "Pronalazac")
    Pronalazac pronalazac;

    @XmlElement(name = "Punomocnik", required = true)
    Punomocnik punomocnik;

    @XmlElement(name = "Podaci_o_dostavljanju", required = true)
    PodaciODostavljanju podaciODostavljanju;

    @XmlElement(name = "Prvobitna_prijava", required = true)
    PrvobitnaPrijava prvobitnaPrijava;

    @XmlElement(name = "Zahtev_za_priznanje_prvenstva")
    List<RanijaPrijava> zahtevZaPriznanjePrvenstva;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class NazivPronalaska {

        @XmlAttribute(name = "jezik", required = true)
        String jezik;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"adresa", "nacin"})
    public static class PodaciODostavljanju {

        @XmlElement(name = "Adresa")
        Adresa adresa;

        @XmlElement(name = "Nacin", required = true)
        String nacin;
    }

}
