package com.xml.project.p1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class TestP1 {

    public void doTest() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.xml.project.p1", TestP1.class.getClassLoader());
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(new File("src/main/resources/xml_files/p1.xml"));
        System.out.println(zahtev);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        updateData(zahtev);
        marshaller.marshal(zahtev, new File("src/main/resources/xml_files/p1.xml"));
    }

    private void updateData(Zahtev zahtev) {
        System.out.println("IZMENJENI PODACI: ");
        PodnosilacPrijave.FizickoLice podnosilacPrijave = (PodnosilacPrijave.FizickoLice) zahtev.getPopunjavaPodnosilac().getPodnosilacPrijave();
        podnosilacPrijave.setIme("Petar");
        podnosilacPrijave.setPrezime("PETROVIC");
        System.out.println("\tIme i prezime podnosioca prijave: 'Petar PETROVIC'");

        zahtev.getPopunjavaPodnosilac().getPodaciODostavljanju().getAdresa().setUlica("Jevrejska");
        System.out.println("\tUlica dostavljanja: 'Jevrejska'");

        zahtev.getPopunjavaPodnosilac().getPunomocnik().setBrojTelefona("065112233");
        System.out.println("\tBroj telefona punomocnika: '065112233'");
    }
}
