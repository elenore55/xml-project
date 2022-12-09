package com.xml.project.z1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;

public class TestZ1 {

    public void doTest() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.xml.project.z1", TestZ1.class.getClassLoader());
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(new File("src/main/resources/xml_files/z1.xml"));
        System.out.println(zahtev);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        updateData(zahtev);
        marshaller.marshal(zahtev, new File("src/main/resources/xml_files/z1.xml"));

        // dodati boju u podatke o zigu
        // tip ziga
    }

    private void updateData(Zahtev zahtev) {
        System.out.println("IZMENJENI PODACI: ");
        Lice.PravnoLice podnosilac = (Lice.PravnoLice) zahtev.getPopunjavaPodnosilac().getPodnosiociPrijave().get(0);
        podnosilac.setPoslovnoIme("Novo poslovno ime");
        System.out.println("\tPoslovno ime podnosioca prijave: 'Novo poslovno ime'" );

        PodaciOZigu podaciOZigu = zahtev.getPopunjavaPodnosilac().getPodaciOZigu();
        podaciOZigu.setTipZiga(PodaciOZigu.TipZiga.INDIVIDUALNI_ZIG);
        System.out.println("\tTip ziga: 'individualni zig'" );

        List<String> boje = zahtev.getPopunjavaPodnosilac().getPodaciOZigu().getBoje();
        boje.set(0, "zuta");
        System.out.println("\tBoje ziga: 'zuta, zelena, bela'" );
    }
}
