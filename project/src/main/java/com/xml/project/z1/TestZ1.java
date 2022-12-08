package com.xml.project.z1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class TestZ1 {

    public void doTest() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.xml.project.z1", TestZ1.class.getClassLoader());
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(new File("src/main/resources/xml_files/z1.xml"));
        System.out.println(zahtev.getPopunjavaZavod());
    }
}
