package com.xml.patent.model;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"naziviPronalaska", "podnosilacPrijave", "pronalazac", "punomocnik",
        "podaciODostavljanju", "prvobitnaPrijava", "zahtevZaPriznanjePrvenstva"})
public class PopunjavaPodnosilac {

    @XmlElement(name = "Naziv_pronalaska", required = true)
    List<NazivPronalaska> naziviPronalaska;

    @XmlElement(name = "Podnosilac_prijave", required = true)
    PodnosilacPrijave podnosilacPrijave;

    @XmlElement(name = "Pronalazac")
    Pronalazac pronalazac;

    @XmlElement(name = "Punomocnik", required = true)
    Punomocnik punomocnik;

    @XmlElement(name = "Podaci_o_dostavljanju", required = true)
    PodaciODostavljanju podaciODostavljanju;

    @XmlElement(name = "Prvobitna_prijava")
    PrvobitnaPrijava prvobitnaPrijava;

    @XmlElementWrapper(name = "Zahtev_za_priznanje_prvenstva")
    @XmlElement(name = "Ranija_prijava")
    List<RanijaPrijava> zahtevZaPriznanjePrvenstva;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("- Popunjava podnosilac:");
        for (var naziv : naziviPronalaska)
            builder.append("\n\t- Naziv pronalaska: ").append(naziv);
        builder.append(podnosilacPrijave);
        if (pronalazac != null)
            builder.append(pronalazac);
        builder.append(punomocnik);
        builder.append(podaciODostavljanju);
        if (prvobitnaPrijava != null)
            builder.append(prvobitnaPrijava);
        if (zahtevZaPriznanjePrvenstva != null) {
            builder.append("\n\t- Zahtev za priznanje prvenstva");
            for (var ranijaPrijava : zahtevZaPriznanjePrvenstva)
                builder.append("\n").append(ranijaPrijava);
        }
        return builder.toString();
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class NazivPronalaska {

        @XmlAttribute(name = "jezik", required = true)
        String jezik;

        @XmlValue
        String naziv;

        @Override
        public String toString() {
            return "\n\t\t- Jezik: " + jezik + "\n\t\t" + "- " + naziv;
        }
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"adresa", "nacin"})
    public static class PodaciODostavljanju {

        public enum NacinDostavljanja {
            ELEKTRONSKI_DOKUMENT, PAPIRNI_DOKUMENT
        }

        @XmlElement(name = "Adresa")
        Adresa adresa;

        @XmlElement(name = "Nacin", required = true)
        NacinDostavljanja nacin;

        @Override
        public String toString() {
            String val = "\n\t- Podaci o dostavljanju: " + "\n\t\t- Nacin: " + nacin;
            if (adresa != null)
                val += "\n\t" + adresa;
            return val;
        }
    }

}
