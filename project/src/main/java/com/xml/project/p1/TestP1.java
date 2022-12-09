package com.xml.project.p1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.Scanner;

public class TestP1 {

    static final String XML_FILE_PATH = "src/main/resources/xml_files/p1.xml";

    public void doTest() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.xml.project.p1", TestP1.class.getClassLoader());
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(new File(XML_FILE_PATH));
        System.out.println(zahtev);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        updateData(zahtev);
        marshaller.marshal(zahtev, new File(XML_FILE_PATH));

        zahtev = (Zahtev) unmarshaller.unmarshal(new File(XML_FILE_PATH));
        System.out.println(zahtev);
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
