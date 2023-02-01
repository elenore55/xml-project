package com.xml.autorsko_pravo.model;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Zahtev")
@XmlType(name = "", propOrder = {"popunjavaPodnosilac", "popunjavaZavod"})
public class Zahtev {

    @XmlElement(name = "Popunjava_podnosilac", required = true)
    PopunjavaPodnosilac popunjavaPodnosilac;

    @XmlElement(name = "Popunjava_zavod", required = true)
    PopunjavaZavod popunjavaZavod;

    @Override
    public int hashCode() {
        return this.popunjavaZavod.getBrojPrijave();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other instanceof Zahtev zahtev) {
            return this.popunjavaZavod.getBrojPrijave() == zahtev.getPopunjavaZavod().getBrojPrijave();
        }
        return false;
    }

    @Override
    public String toString() {
        return "ZAHTEV A1\n" + popunjavaPodnosilac + "\n" + popunjavaZavod + "\n";
    }
}
