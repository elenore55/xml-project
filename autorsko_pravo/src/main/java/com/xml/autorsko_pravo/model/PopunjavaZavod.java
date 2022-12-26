package com.xml.autorsko_pravo.model;

import com.xml.autorsko_pravo.util.Util;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"prilog", "brojPrijave", "datumPodnosenja"})
public class PopunjavaZavod {

    @XmlElement(name = "Prilog", required = true)
    Prilog prilog;

    @XmlElement(name = "Broj_prijave", required = true)
    int brojPrijave;

    @XmlElement(name = "Datum_podnosenja", required = true)
    @XmlSchemaType(name = "date")
    Date datumPodnosenja;

    @Override
    public String toString() {
        return "- Popunjava zavod: \n\t" + prilog + "\n\t- Broj prijave: " + brojPrijave + "\n\t- Datum podnosenja: "
                + Util.dateToStr(datumPodnosenja);
    }
}
