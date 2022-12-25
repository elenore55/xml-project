package com.xml.project.a1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TestA1 {

    static final String XML_PATH = "src/main/resources/xml/a1.xml";
    static final String XSD_PATH = "src/main/resources/xsd/A1.xsd";

    public void doTest() throws JAXBException, SAXException {
        JAXBContext context = JAXBContext.newInstance("com.xml.project.a1", TestA1.class.getClassLoader());
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File(XSD_PATH));

        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setSchema(schema);
        Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(new File(XML_PATH));
        System.out.println(zahtev);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setSchema(schema);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(zahtev, new File(XML_PATH));
    }

    private void updateData(Zahtev zahtev) {
        System.out.println("IZMENA PODATAKA: ");
        Scanner scanner = new Scanner(System.in);

        Autor zivAutor = zahtev.getPopunjavaPodnosilac().getAutori().get(0);
        System.out.print("\tIme autora: ");
        zivAutor.setIme(scanner.nextLine());
        System.out.print("\tPrezime autora: ");
        zivAutor.setPrezime(scanner.nextLine());

        Prilog.OpisDela prilog = (Prilog.OpisDela) zahtev.getPopunjavaZavod().getPrilog();
        System.out.print("\tOpis dela: ");
        prilog.setOpis(scanner.nextLine());
        zahtev.getPopunjavaZavod().setDatumPodnosenja(new Date());

        System.out.print("\tNaslov dela: ");
        zahtev.getPopunjavaPodnosilac().getAutorskoDelo().getPodaciONaslovu().setNaslov(scanner.nextLine());
        System.out.print("\tAlternativni naslov dela: ");
        zahtev.getPopunjavaPodnosilac().getAutorskoDelo().getPodaciONaslovu().setAlternativniNaslov(scanner.nextLine());
        System.out.println();
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
