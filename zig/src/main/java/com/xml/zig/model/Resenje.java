package com.xml.zig.model;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Resenje")
@XmlType(name = "TResenje", propOrder = {"datumResenja", "imeSluzbenika", "prezimeSluzbenika", "referenca"})
public abstract class Resenje {

    @XmlElement(name = "Datum_resenja", required = true)
    protected Date datumResenja;

    @XmlElement(name = "Ime_sluzbenika", required = true)
    protected String imeSluzbenika;

    @XmlElement(name = "Prezime_sluzbenika", required = true)
    protected String prezimeSluzbenika;

    @XmlElement(name = "Referenca", required = true)
    protected String referenca;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TOdobrenZahtev", propOrder = {"sifra"})
    public static class OdobrenZahtev extends Resenje {

        @XmlElement(name = "Sifra", required = true)
        String sifra;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TOdbijenZahtev", propOrder = {"obrazlozenje"})
    public static class OdbijenZahtev extends Resenje {

        @XmlElement(name = "Obrazlozenje", required = true)
        String obrazlozenje;
    }
}
