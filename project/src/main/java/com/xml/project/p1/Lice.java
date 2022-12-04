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
@XmlType(name = "TLice", propOrder = {"adresa", "brojTelefona", "ePosta"})
public abstract class Lice {

    @XmlElement(name = "Adresa", required = true)
    protected Adresa adresa;

    @XmlElement(name = "Broj_telefona", required = true)
    protected String brojTelefona;

    @XmlElement(name = "E_posta", required = true)
    protected String ePosta;
}
