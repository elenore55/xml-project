package com.xml.project.z1;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"primerakZnaka", "spisakRobeIUsluga", "punomocje", "generalnoPunomocje",
        "punomocjeNaknadnoDostavljeno", "opstiAkt", "dokazOPravuPrvenstva", "dokazOUplatiTakse"})
public class Prilozi {

    @XmlElement(name = "Primerak_znaka", required = true)
    String primerakZnaka;

    @XmlElementWrapper(name = "Spisak_robe_i_usluga")
    @XmlElement(name = "Roba_ili_usluga", required = true)
    List<String> spisakRobeIUsluga;

    @XmlElement(name = "Punomocje", required = true)
    String punomocje;

    @XmlElement(name = "Generalno_punomocje", required = true)
    String generalnoPunomocje;

    @XmlElement(name = "Punomocje_naknadno_dostavljeno", required = true)
    boolean punomocjeNaknadnoDostavljeno;

    @XmlElement(name = "Opsti_akt", required = true)
    String opstiAkt;

    @XmlElement(name = "Dokaz_o_pravu_prvenstva", required = true)
    String dokazOPravuPrvenstva;

    @XmlElement(name = "Dokaz_o_uplati_takse", required = true)
    String dokazOUplatiTakse;
}
