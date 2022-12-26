package com.xml.zig.model;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public Adresa createAdresa() {
        return new Adresa();
    }

    public PopunjavaZavod.BrojPrijaveZiga createBrojPrijaveZiga() {
        return new PopunjavaZavod.BrojPrijaveZiga();
    }

    public Lice.FizickoLice createFizickoLice() {
        return new Lice.FizickoLice();
    }

    public PlaceneTakse.KlasaTaksa createKlasaTaksa() {
        return new PlaceneTakse.KlasaTaksa();
    }

    public PlaceneTakse createPlaceneTakse() {
        return new PlaceneTakse();
    }

    public PodaciOZigu createPodaciOZigu() {
        return new PodaciOZigu();
    }

    public PopunjavaPodnosilac createPopunjavaPodnosilac() {
        return new PopunjavaPodnosilac();
    }

    public PopunjavaZavod createPopunjavaZavod() {
        return new PopunjavaZavod();
    }

    public Lice.PravnoLice createPravnoLice() {
        return new Lice.PravnoLice();
    }

    public Prilozi createPrilozi() {
        return new Prilozi();
    }

    public Zahtev createZahtev() {
        return new Zahtev();
    }
}
