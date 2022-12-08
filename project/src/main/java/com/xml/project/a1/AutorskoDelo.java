package com.xml.project.a1;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TAutorsko_delo", propOrder = {"nacinKoriscenja", "vrsta", "formaZapisa", "podaciONaslovu"})
public class AutorskoDelo {

    @XmlAttribute(name = "stvoreno_u_radnom_odnosu", required = true)
    boolean stvorenoURadnomOdnosu;

    @XmlElement(name = "Nacin_koriscenja", required = true)
    String nacinKoriscenja;

    @XmlElement(name = "Vrsta", required = true)
    String vrsta;

    @XmlElement(name = "Forma_zapisa", required = true)
    String formaZapisa;

    @XmlElement(name = "Podaci_o_naslovu", required = true)
    PodaciONaslovu podaciONaslovu;

    @Override
    public String toString() {
        String radniOdnos = stvorenoURadnomOdnosu ? "Stvoreno u radnom odnosu" : "Nije stvoreno u radnom odnosu";
        return "- Autorsko delo: " +
                "\n\t\t- " + radniOdnos +
                "\n\t\t- Vrsta: " + vrsta +
                "\n\t\t- Forma zapisa: " + formaZapisa +
                "\n\t" + podaciONaslovu;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TDelo_prerade", propOrder = {"izvornoDelo"})
    public static class DeloPrerade extends AutorskoDelo {

        @XmlElement(name = "Izvorno_delo", required = true)
        IzvornoDelo izvornoDelo;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"podaciONaslovu", "autori"})
    public static class IzvornoDelo {
        @XmlElement(name = "Podaci_o_naslovu", required = true)
        PodaciONaslovu podaciONaslovu;

        @XmlElementWrapper(name = "Autori")
        @XmlElement(name = "Autor")
        List<Autor> autori;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"naslov", "alternativniNaslov"})
    public static class PodaciONaslovu {

        @XmlElement(name = "Naslov", required = true)
        String naslov;

        @XmlElement(name = "Alternativni_naslov")
        String alternativniNaslov;

        @Override
        public String toString() {
            String val = "\t- Podaci o naslovu: " + "\n\t\t\t- Naslov: " + naslov;
            if (alternativniNaslov != null) {
                val += "\n\t\t\t- Alternativni naslov: " + alternativniNaslov;
            }
            return val;
        }

    }
}
