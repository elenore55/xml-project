package com.xml.project.p1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPronalazac", propOrder = {"brojFaksa", "ime", "prezime"})
public class Pronalazac extends Lice {

    @XmlElement(name = "Broj_faksa", required = true)
    String brojFaksa;

    @XmlElement(name = "Ime", required = true)
    String ime;

    @XmlElement(name = "Prezime", required = true)
    String prezime;
}
