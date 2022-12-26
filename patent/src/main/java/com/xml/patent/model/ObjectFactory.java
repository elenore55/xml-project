package com.xml.patent.model;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public Zahtev createZahtev() {
        return new Zahtev();
    }

    public Adresa createAdresa() {
        return new Adresa();
    }

    public PopunjavaPodnosilac.NazivPronalaska createNazivPronalaska() {
        return new PopunjavaPodnosilac.NazivPronalaska();
    }

    public PopunjavaPodnosilac.PodaciODostavljanju createPodaciODostavljanju() {
        return new PopunjavaPodnosilac.PodaciODostavljanju();
    }

    public PodnosilacPrijave.FizickoLice createPodnosilacFizickoLice() {
        return new PodnosilacPrijave.FizickoLice();
    }

    public PodnosilacPrijave.PravnoLice createPodnosilacPravnoLice() {
        return new PodnosilacPrijave.PravnoLice();
    }

    public PopunjavaPodnosilac createPopunjavaPodnosilac() {
        return new PopunjavaPodnosilac();
    }

    public PopunjavaZavod createPopunjavaZavod() {
        return new PopunjavaZavod();
    }

    public Pronalazac createPronalazac() {
        return new Pronalazac();
    }

    public PrvobitnaPrijava createPrvobitnaPrijava() {
        return new PrvobitnaPrijava();
    }

    public Punomocnik.FizickoLice createPunomocnikFizickoLice() {
        return new Punomocnik.FizickoLice();
    }

    public Punomocnik.PravnoLice createPunoomocnikPravnoLice() {
        return new Punomocnik.PravnoLice();
    }

    public RanijaPrijava createRanijaPrijava() {
        return new RanijaPrijava();
    }
}
