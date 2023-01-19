package com.xml.autorsko_pravo.model;

import com.xml.autorsko_pravo.dto.AdresaDTO;
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
@XmlType(name = "", propOrder = {"mesto", "postanskiBroj", "ulica", "brojObjekta"})
public class Adresa {

    public Adresa(AdresaDTO dto) {
        this.ulica = dto.getUlica();
        this.brojObjekta = dto.getBroj();
        this.mesto = dto.getMesto();
        this.postanskiBroj = dto.getPostanskiBroj();
    }

    @XmlElement(name = "Mesto", required = true)
    String mesto;

    @XmlElement(name = "Postanski_broj", required = true)
    int postanskiBroj;

    @XmlElement(name = "Ulica", required = true)
    String ulica;

    @XmlElement(name = "Broj", required = true)
    int brojObjekta;

    @Override
    public String toString() {
        return "\t- Adresa: " + mesto + " " + postanskiBroj + " " + ulica + " " + brojObjekta;
    }
}
