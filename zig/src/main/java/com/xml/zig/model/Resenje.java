package com.xml.zig.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Resenje")
@XmlType(name = "TResenje", propOrder = {"datumResenja", "imeSluzbenika", "prezimeSluzbenika", "referenca"})
public class Resenje {

    @XmlElement(name = "Datum_resenja", required = true)
    @XmlSchemaType(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    protected Date datumResenja;

    @XmlElement(name = "Ime_sluzbenika", required = true)
    protected String imeSluzbenika;

    @XmlElement(name = "Prezime_sluzbenika", required = true)
    protected String prezimeSluzbenika;

    @XmlElement(name = "Referenca", required = true)
    protected String referenca;

    @Override
    public int hashCode() {
        return this.referenca.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other instanceof Resenje resenje) {
            return this.referenca.equals(resenje.getReferenca());
        }
        return false;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "OdobrenZahtev")
    @XmlType(name = "TOdobrenZahtev", propOrder = {"sifra"})
    public static class OdobrenZahtev extends Resenje {

        @XmlElement(name = "Sifra", required = true)
        String sifra;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "OdbijenZahtev")
    @XmlType(name = "TOdbijenZahtev", propOrder = {"obrazlozenje"})
    public static class OdbijenZahtev extends Resenje {

        @XmlElement(name = "Obrazlozenje", required = true)
        String obrazlozenje;
    }
}
