package com.xml.project.a1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TAutor", propOrder = {"ime", "prezime"})
public abstract class Autor {

    @XmlElement(name = "Ime", required = true)
    protected String ime;

    @XmlElement(name = "Prezime", required = true)
    protected String prezime;

    @Override
    public String toString() {
        return "\n\t\t- Ime: " + ime + "\n\t\t- Prezime: " + prezime;
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TZiv_autor", propOrder = {"adresa", "drzavljanstvo"})
    public static class ZivAutor extends Autor {

        @XmlElement(name = "Adresa", required = true)
        Adresa adresa;

        @XmlElement(name = "Drzavljanstvo", required = true)
        String drzavljanstvo;

        @Override
        public String toString() {
            return super.toString() + "\n\t" + adresa + "\n\t\t- Drzavljanstvo: " + drzavljanstvo + "\n";
        }
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TPokojni_autor", propOrder = {"godinaSmrti"})
    public static class PokojniAutor extends Autor {

        @XmlElement(name = "Godina_smrti", required = true)
        int godinaSmrti;

        @Override
        public String toString() {
            return super.toString() + "\n\t\t- Godina smrti: " + godinaSmrti + "\n";
        }
    }
}
