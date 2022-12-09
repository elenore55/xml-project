package com.xml.project.z1;

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
}
