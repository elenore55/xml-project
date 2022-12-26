package com.xml.autorsko_pravo.model;

import jakarta.xml.bind.annotation.XmlRegistry;
import lombok.NoArgsConstructor;

@XmlRegistry
@NoArgsConstructor
public class ObjectFactory {

    public Adresa createAdresa() {
        return new Adresa();
    }

    public AutorskoDelo createAutorskoDelo() {
        return new AutorskoDelo();
    }

    public Podnosilac.FizickoLice createFizickoLice() {
        return new Podnosilac.FizickoLice();
    }

    public Prilog.OpisDela createOpisDela() {
        return new Prilog.OpisDela();
    }

    public AutorskoDelo.PodaciONaslovu createPodaciONaslovu() {
        return new AutorskoDelo.PodaciONaslovu();
    }

    public Autor.PokojniAutor createPokojniAutor() {
        return new Autor.PokojniAutor();
    }

    public PopunjavaZavod createPopunjavaZavod() {
        return new PopunjavaZavod();
    }

    public PopunjavaPodnosilac createPopunjavaPodnosilac() {
        return new PopunjavaPodnosilac();
    }

    public Podnosilac.PravnoLice createPravnoLice() {
        return new Podnosilac.PravnoLice();
    }

    public Prilog.PrimerDela createPrimerDela() {
        return new Prilog.PrimerDela();
    }

    public Podnosilac.Punomocnik createPunomocnik() {
        return new Podnosilac.Punomocnik();
    }

    public Zahtev createZahtev() {
        return new Zahtev();
    }

    public Autor.ZivAutor createZivAutor() {
        return new Autor.ZivAutor();
    }

    public AutorskoDelo.DeloPrerade createDeloPrerade() {
        return new AutorskoDelo.DeloPrerade();
    }

    public AutorskoDelo.IzvornoDelo createIzvornoDelo() {
        return new AutorskoDelo.IzvornoDelo();
    }
}
