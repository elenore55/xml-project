package com.xml.patent.model;

import com.xml.patent.dto.AdresaDTO;
import com.xml.patent.dto.CreateZahtevDTO;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"naziviPronalaska", "podnosilacPrijave", "pronalazac", "punomocnik",
        "podaciODostavljanju", "prvobitnaPrijava", "zahtevZaPriznanjePrvenstva"})
public class PopunjavaPodnosilac {

    public PopunjavaPodnosilac(CreateZahtevDTO dto) {
        var nazivSrpski = new NazivPronalaska(dto.getNazivSrpski(), "srpski");
        var nazivEngleski = new NazivPronalaska(dto.getNazivEngleski(), "engleski");
        this.naziviPronalaska = Arrays.asList(nazivSrpski, nazivEngleski);
        if (dto.getPodnosilac().isFizickoLice())
            this.podnosilacPrijave = new PodnosilacPrijave.FizickoLice(dto.getPodnosilac());
        else this.podnosilacPrijave = new PodnosilacPrijave.PravnoLice(dto.getPodnosilac());
        if (!dto.getPronalazac().isAnoniman() && !dto.getPodnosilac().isPronalazac())
            this.pronalazac = new Pronalazac(dto.getPronalazac());
        if (dto.getPunomocnik().isFizickoLice()) this.punomocnik = new Punomocnik.FizickoLice(dto.getPunomocnik());
        else this.punomocnik = new Punomocnik.PravnoLice(dto.getPunomocnik());
        this.podaciODostavljanju = new PodaciODostavljanju(dto.getAdresaDostavljanja(), dto.getNacinDostavljanja());
        if (dto.getVrstaPrijave().equals("DOPUNSKA"))
            this.prvobitnaPrijava = new PrvobitnaPrijava(dto.getBrojPrvobitnePrijave(), dto.getDatumPrvobitnePrijave());
        if (dto.getRanijePrijaveList() != null && dto.getRanijePrijaveList().size() > 0) {
            this.zahtevZaPriznanjePrvenstva = new ArrayList<>();
            for (var rp : dto.getRanijePrijaveList()) {
                zahtevZaPriznanjePrvenstva.add(new RanijaPrijava(rp));
            }
        }
    }

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
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class NazivPronalaska {

        public NazivPronalaska(String naziv, String jezik) {
            this.naziv = naziv;
            this.jezik = jezik;
        }

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
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"adresa", "nacin"})
    public static class PodaciODostavljanju {

        public enum NacinDostavljanja {
            ELEKTRONSKI_DOKUMENT, PAPIRNI_DOKUMENT
        }

        public PodaciODostavljanju(AdresaDTO adresa, String nacin) {
            if (adresa != null && adresa.getUlica() != null) this.adresa = new Adresa(adresa);
            this.nacin = NacinDostavljanja.valueOf(nacin);
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
