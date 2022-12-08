package com.xml.project.a1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPrilog")
public abstract class Prilog {

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TOpis_dela", propOrder = {"opis"})
    public static class OpisDela extends Prilog {

        @XmlElement(name = "Opis", required = true)
        String opis;

        @Override
        public String toString() {
            return "- Opis: " + opis;
        }
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TPrimer_dela", propOrder = {"primer"})
    public static class PrimerDela {

        @XmlElement(name = "Primer", required = true)
        String primer;

        @Override
        public String toString() {
            return "- Primer dela: " + primer;
        }
    }
}
