package com.xml.zig.model;

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
    public String toString() {
        return "\nZAHTEV Z1\n" + popunjavaPodnosilac + "\n" + popunjavaZavod + "\n";
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other instanceof Zahtev zahtev) {
            return this.getPopunjavaZavod().getBrojPrijaveZiga().equals(zahtev.getPopunjavaZavod().getBrojPrijaveZiga());
        }
        return false;
    }

    @Override
    public int hashCode() {
        var str = String.format("%s-%s", popunjavaZavod.getBrojPrijaveZiga().getId(), popunjavaZavod.getBrojPrijaveZiga().getGodina());
        return str.hashCode();
    }
}
