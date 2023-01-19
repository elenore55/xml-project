package com.xml.autorsko_pravo.model;

import com.xml.autorsko_pravo.dto.AutorskoDeloDTO;
import com.xml.autorsko_pravo.dto.IzvornoDeloDTO;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TAutorsko_delo", propOrder = {"nacinKoriscenja", "vrsta", "formaZapisa", "podaciONaslovu"})
public class AutorskoDelo {

    public AutorskoDelo(AutorskoDeloDTO dto) {
        this.stvorenoURadnomOdnosu = dto.isRadniOdnos();
        this.nacinKoriscenja = dto.getNacinKoriscenja();
        this.vrsta = dto.getVrsta();
        this.formaZapisa = dto.getFormaZapisa();
        this.podaciONaslovu = new PodaciONaslovu();
        podaciONaslovu.setNaslov(dto.getNaslov());
        if (!dto.getAltNaslov().equals("")) {
            podaciONaslovu.setAlternativniNaslov(dto.getAltNaslov());
        }
    }

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
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TDelo_prerade", propOrder = {"izvornoDelo"})
    public static class DeloPrerade extends AutorskoDelo {

        public DeloPrerade(AutorskoDeloDTO dto) {
            super(dto);
            this.izvornoDelo = new IzvornoDelo(dto.getIzvornoDelo());
        }

        @XmlElement(name = "Izvorno_delo", required = true)
        IzvornoDelo izvornoDelo;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"podaciONaslovu", "autori"})
    public static class IzvornoDelo {

        public IzvornoDelo(IzvornoDeloDTO dto) {
            this.podaciONaslovu = new PodaciONaslovu();
            podaciONaslovu.setNaslov(dto.getNaslov());
            if (!dto.getAltNaslov().equals("")) {
                podaciONaslovu.setAlternativniNaslov(dto.getAltNaslov());
            }
            autori = new ArrayList<>();
            for (var a : dto.getAutori()) {
                if (a.isZiv())
                    autori.add(new Autor.ZivAutor(a));
                else
                    autori.add(new Autor.PokojniAutor(a));
            }
        }

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
