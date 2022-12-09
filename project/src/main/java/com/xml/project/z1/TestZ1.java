package com.xml.project.z1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class TestZ1 {

    static final String XML_FILE_PATH = "src/main/resources/xml_files/z1.xml";

    public void doTest() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.xml.project.z1", TestZ1.class.getClassLoader());
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
        Lice.PravnoLice podnosilac = (Lice.PravnoLice) zahtev.getPopunjavaPodnosilac().getPodnosiociPrijave().get(0);
        System.out.print("\tPoslovno ime podnosioca prijave: ");
        podnosilac.setPoslovnoIme(scanner.nextLine());

        PodaciOZigu podaciOZigu = zahtev.getPopunjavaPodnosilac().getPodaciOZigu();
        System.out.print("\tVrsta znaka: ");
        podaciOZigu.setVrstaZnaka(scanner.nextLine());

        List<String> boje = zahtev.getPopunjavaPodnosilac().getPodaciOZigu().getBoje();
        System.out.print("\tBoja ziga: ");
        boje.add(scanner.nextLine());
    }
}
