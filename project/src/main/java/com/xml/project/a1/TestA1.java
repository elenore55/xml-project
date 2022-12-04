package com.xml.project.a1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.Date;
import java.util.List;

public class TestA1 {

    public void doTest() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.xml.project.a1", TestA1.class.getClassLoader());

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        Zahtev zahtev = createZahtev();
        marshaller.marshal(zahtev, new File("src/main/resources/xml_files/a1.xml"));

        Unmarshaller unmarshaller = context.createUnmarshaller();
        zahtev = (Zahtev) unmarshaller.unmarshal(new File("src/main/resources/xml_files/a1.xml"));
        System.out.println(zahtev);
    }

    private Zahtev createZahtev() {
        Zahtev z = new Zahtev();
        z.setPopunjavaZavod(createPopunjavaZavod());
        z.setPopunjavaPodnosilac(createPopunjavaPodnosilac());
        return z;
    }

    private Adresa createAdresa(String mesto, String ulica, int postanskiBroj, int broj) {
        Adresa a = new Adresa();
        a.setMesto(mesto);
        a.setBrojObjekta(broj);
        a.setPostanskiBroj(postanskiBroj);
        a.setUlica(ulica);
        return a;
    }

    private Autor createZivAutor() {
        Autor.ZivAutor a = new Autor.ZivAutor();
        a.setIme("Milica");
        a.setPrezime("Popovic");
        a.setAdresa(createAdresa("Novi Sad", "Radnicka", 21000, 23));
        a.setDrzavljanstvo("Srbija");
        return a;
    }

    private Autor createPokojniAutor() {
        Autor.PokojniAutor a = new Autor.PokojniAutor();
        a.setIme("Pero");
        a.setPrezime("Peric");
        a.setGodinaSmrti(1990);
        return a;
    }

    private Podnosilac createPodnosilac() {
        Podnosilac.PravnoLice f = new Podnosilac.PravnoLice();
        f.setAdresa(createAdresa("Beograd", "Knez Mihajlova", 11000, 11));
        f.setPoslovnoIme("Poslovnica");
        return f;
    }

    private PopunjavaZavod createPopunjavaZavod() {
        PopunjavaZavod p = new PopunjavaZavod();
        p.setBrojPrijave(23);
        p.setDatumPodnosenja(new Date());
        p.setPrilog(createPrilog());
        return p;
    }

    private Prilog createPrilog() {
        Prilog.OpisDela o = new Prilog.OpisDela();
        o.setOpis("neki opis");
        return o;
    }

    private AutorskoDelo createDelo() {
        AutorskoDelo a = new AutorskoDelo();
        a.setStvorenoURadnomOdnosu(true);
        a.setFormaZapisa("knjiga");
        a.setNacinKoriscenja("citanje");
        a.setVrsta("knjizevno djelo");
        a.setPodaciONaslovu(createNaslov());
        return a;
    }

    private AutorskoDelo.PodaciONaslovu createNaslov() {
        AutorskoDelo.PodaciONaslovu p = new AutorskoDelo.PodaciONaslovu();
        p.setNaslov("Moj naslov");
        p.setAlternativniNaslov("ALT");
        return p;
    }

    private PopunjavaPodnosilac createPopunjavaPodnosilac() {
        PopunjavaPodnosilac p = new PopunjavaPodnosilac();
        p.setPodnosilac(createPodnosilac());
        p.setAutori(List.of(createZivAutor(), createPokojniAutor()));
        p.setAutorskoDelo(createDelo());
        return p;
    }
}
