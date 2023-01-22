package com.xml.zig.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"primerakZnaka", "spisakRobeIUsluga", "punomocje", "generalnoPunomocje",
        "punomocjeNaknadnoDostavljeno", "opstiAkt", "dokazOPravuPrvenstva", "dokazOUplatiTakse"})
public class Prilozi {

    public Prilozi(Map<String, String> fileNames, boolean generalnoPunomocje, boolean punomocjeNaknadnoDostavljeno) {
        this.generalnoPunomocje = generalnoPunomocje;
        this.punomocjeNaknadnoDostavljeno = punomocjeNaknadnoDostavljeno;
        this.primerakZnaka = fileNames.get("primerakZnaka");
        this.spisakRobeIUsluga = fileNames.get("spisakRobeIUsluga");
        this.punomocje = fileNames.get("punomocje");
        this.opstiAkt = fileNames.get("opstiAkt");
        this.dokazOPravuPrvenstva = fileNames.get("dokazOPravuPrvenstva");
        this.dokazOUplatiTakse = fileNames.get("dokazOUplatiTakse");
    }

    @XmlElement(name = "Primerak_znaka", required = true)
    String primerakZnaka;

    @XmlElement(name = "Spisak_robe_i_usluga")
    String spisakRobeIUsluga;

    @XmlElement(name = "Punomocje", required = true)
    String punomocje;

    @XmlElement(name = "Generalno_punomocje", required = true)
    boolean generalnoPunomocje;

    @XmlElement(name = "Punomocje_naknadno_dostavljeno", required = true)
    boolean punomocjeNaknadnoDostavljeno;

    @XmlElement(name = "Opsti_akt", required = true)
    String opstiAkt;

    @XmlElement(name = "Dokaz_o_pravu_prvenstva", required = true)
    String dokazOPravuPrvenstva;

    @XmlElement(name = "Dokaz_o_uplati_takse", required = true)
    String dokazOUplatiTakse;

    @Override
    public String toString() {
        return "\t- Prilozi:" + "\n\t\t- Primerak znaka: " + primerakZnaka +
                "\n\t\t- Spisak robe i usluga: " +
                spisakRobeIUsluga +
                "\n\t\t- Punomocje: " + punomocje +
                "\n\t\t- Generalno punomocje: " + generalnoPunomocje +
                "\n\t\t- Punomocje naknadno dostavljeno: " + punomocjeNaknadnoDostavljeno +
                "\n\t\t- Opsti akt: " + opstiAkt +
                "\n\t\t- Dokaz o pravu prvenstva: " + dokazOPravuPrvenstva +
                "\n\t\t- Dokaz o uplati takse: " + dokazOUplatiTakse;
    }
}
