package com.xml.patent.model;

import com.xml.patent.util.Util;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"broj", "datumPodnosenja"})
public class PrvobitnaPrijava {

    public PrvobitnaPrijava(int broj, String datum) {
        this.broj = broj;
        this.datumPodnosenja = Util.strToDate(datum);
    }

    @XmlElement(name = "Broj", required = true)
    int broj;

    @XmlElement(name = "Datum_podnosenja", required = true)
    @XmlSchemaType(name = "date")
    Date datumPodnosenja;

    @Override
    public String toString() {
        return "\n\t- Prvobitna prijava: " + "\n\t\t- Broj prijave: " + broj + "\n\t\t- Datum podnosenja: " + Util.dateToStr(datumPodnosenja);
    }
}
