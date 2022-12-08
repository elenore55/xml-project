package com.xml.project.p1;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"broj", "datumPodnosenja"})
public class PrvobitnaPrijava {

    @XmlElement(name = "Broj", required = true)
    int broj;

    @XmlElement(name = "Datum_podnosenja", required = true)
    @XmlSchemaType(name = "date")
    Date datumPodnosenja;

    @Override
    public String toString() {
        return "\n\t- Prvobitna prijava: " + "\n\t\t- Broj prijave: " + broj + "\n\t\t- Datum podnosenja: " + datumPodnosenja;
    }
}
