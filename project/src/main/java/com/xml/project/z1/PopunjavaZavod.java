package com.xml.project.z1;

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

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"id", "godina"})
    public static class BrojPrijaveZiga {

        @XmlElement(name = "Id", required = true)
        int id;

        @XmlElement(name = "Godina", required = true)
        int godina;
    }
}
