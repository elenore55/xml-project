package com.xml.project.z1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

public class TestZ1 {

    public void doTest() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.xml.project.z1", TestZ1.class.getClassLoader());
    }
}
