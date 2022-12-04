package com.xml.project.a1;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "zahtev")
@XmlType(name = "", propOrder = {"popunjavaPodnosilac", "popunjavaZavod"})
public class Zahtev {

    @XmlElement(name = "popunjavaPodnosilac", required = true)
    PopunjavaPodnosilac popunjavaPodnosilac;

    @XmlElement(name = "popunjavaZavod", required = true)
    PopunjavaZavod popunjavaZavod;

    @Override
    public String toString() {
        return popunjavaPodnosilac + "\n" + popunjavaZavod;
    }
}
