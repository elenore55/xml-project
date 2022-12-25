package com.xml.project.p1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.Scanner;

public class TestP1 {

    static final String XML_PATH = "src/main/resources/xml/p1.xml";
    static final String XSD_PATH = "src/main/resources/xsd/P1.xsd";

    public void doTest() throws JAXBException, SAXException {
        JAXBContext context = JAXBContext.newInstance("com.xml.project.p1", TestP1.class.getClassLoader());
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
        PodnosilacPrijave.FizickoLice podnosilacPrijave = (PodnosilacPrijave.FizickoLice) zahtev.getPopunjavaPodnosilac().getPodnosilacPrijave();
        System.out.print("\tIme podnosioca prijave: ");
        podnosilacPrijave.setIme(scanner.nextLine());
        System.out.print("\tPrezime podnosioca prijave: ");
        podnosilacPrijave.setPrezime(scanner.nextLine().toUpperCase());

        System.out.print("\tUlica dostavljanja: ");
        zahtev.getPopunjavaPodnosilac().getPodaciODostavljanju().getAdresa().setUlica(scanner.nextLine());

        System.out.print("\tBroj telefona punomocnika: ");
        zahtev.getPopunjavaPodnosilac().getPunomocnik().setBrojTelefona(scanner.nextLine());
    }
}
