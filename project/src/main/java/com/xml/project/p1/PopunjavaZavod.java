package com.xml.project.p1;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"brojPrijave", "datumPrijema", "datumPodnosenja"})
public class PopunjavaZavod {

    @XmlElement(name = "Broj_prijave", required = true)
    int brojPrijave;

    @XmlElement(name = "Datum_prijema", required = true)
    @XmlSchemaType(name = "date")
    Date datumPrijema;

    @XmlElement(name = "Datum_podnosenja", required = true)
    @XmlSchemaType(name = "date")
    Date datumPodnosenja;

    @Override
    public String toString() {
        return "- Popunjava zavod:" + "\n\t- Broj prijave: " + brojPrijave + "\n\t- Datum prijema: " + datumPrijema + "\n\t- Datum podnosenja: " + datumPodnosenja;
    }

}
