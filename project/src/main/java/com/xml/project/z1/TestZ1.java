package com.xml.project.z1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class TestZ1 {

    static final String XML_PATH = "src/main/resources/xml/z1.xml";
    static final String XSD_PATH = "src/main/resources/xsd/Z1.xsd";

    public void doTest() throws JAXBException, SAXException {
        JAXBContext context = JAXBContext.newInstance("com.xml.project.z1", TestZ1.class.getClassLoader());
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
