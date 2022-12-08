package com.xml.project.p1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class TestP1 {

    public void doTest() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.xml.project.p1", TestP1.class.getClassLoader());
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(new File("src/main/resources/xml_files/p1.xml"));
        System.out.println(zahtev.getPopunjavaPodnosilac());
    }
}
