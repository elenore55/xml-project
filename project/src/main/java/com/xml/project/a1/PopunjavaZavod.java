package com.xml.project.a1;

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
}
