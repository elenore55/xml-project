package com.xml.patent.model;

import com.xml.patent.util.Util;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"broj", "datumPodnosenja", "oznaka"})
public class RanijaPrijava {

    @XmlElement(name = "Broj", required = true)
    int broj;

    @XmlElement(name = "Datum_podnosenja", required = true)
    @XmlSchemaType(name = "date")
    Date datumPodnosenja;

    @XmlElement(name = "Oznaka", required = true)
    String oznaka;

    @Override
    public String toString() {
        return "\t\t- Ranija prijava: " + "\n\t\t\t- Broj prijave: " + broj + "\n\t\t\t- Datum podnosenja: "
                + Util.dateToStr(datumPodnosenja) + "\n\t\t\t- Oznaka: " + oznaka;
    }
}
