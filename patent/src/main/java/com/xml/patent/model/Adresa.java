package com.xml.patent.model;

import com.xml.patent.dto.AdresaDTO;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"ulica", "brojObjekta", "postanskiBroj", "mesto", "drzava"})
public class Adresa {

    public Adresa(AdresaDTO dto) {
        this.ulica = dto.getUlica();
        this.brojObjekta = dto.getBroj();
        this.mesto = dto.getMesto();
        this.postanskiBroj = dto.getPostanskiBroj();
        if (dto.getDrzava() != null && !dto.getDrzava().equals(""))
            this.drzava = dto.getDrzava();
    }

    @XmlElement(name = "Ulica", required = true)
    String ulica;

    @XmlElement(name = "Broj", required = true)
    int brojObjekta;

    @XmlElement(name = "Postanski_broj", required = true)
    int postanskiBroj;
    @XmlElement(name = "Mesto", required = true)
    String mesto;

    @XmlElement(name = "Drzava")
    String drzava;


    @Override
    public String toString() {
        return "\t- Adresa: " + mesto + " " + postanskiBroj + " " + ulica + " " + brojObjekta;
    }
}
