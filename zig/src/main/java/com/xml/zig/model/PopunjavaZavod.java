package com.xml.zig.model;

import com.xml.zig.util.Util;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"brojPrijaveZiga", "datumPodnosenja", "prilozi"})
public class PopunjavaZavod {

    @XmlElement(name = "Broj_prijave_ziga", required = true)
    BrojPrijaveZiga brojPrijaveZiga;

    @XmlElement(name = "Datum_podnosenja", required = true)
    @XmlSchemaType(name = "date")
    Date datumPodnosenja;

    @XmlElement(name = "Prilozi")
    Prilozi prilozi;

    @Override
    public String toString() {
        return "- Popunjava zavod:" + "\n" + brojPrijaveZiga + "\n\t- Datum podnosenja: " + Util.dateToStr(datumPodnosenja) + "\n" + prilozi;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"id", "godina"})
    public static class BrojPrijaveZiga {

        @XmlElement(name = "Id", required = true)
        int id;

        @XmlElement(name = "Godina", required = true)
        int godina;

        @Override
        public String toString() {
            return "\t- Broj prijave ziga: Z-" + id + "/" + godina;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) return false;
            if (other instanceof BrojPrijaveZiga broj) {
                return broj.getId() == this.id && broj.getGodina() == this.godina;
            }
            return false;
        }
    }
}
