package com.xml.project.p1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

public class TestP1 {

    public void doTest() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.xml.project.p1", TestP1.class.getClassLoader());
    }
}
