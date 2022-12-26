package com.xml.patent;

import com.xml.patent.service.Marshalling;
import jakarta.xml.bind.JAXBException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.SAXException;

@SpringBootApplication
public class PatentApplication {

    public static void main(String[] args) throws JAXBException, SAXException {
        SpringApplication.run(PatentApplication.class, args);
        var m = new Marshalling();
        var zahtev = m.unmarshal("p1.xml");
        System.out.println(zahtev);
        m.marshall(zahtev, "p1.xml");
    }

}
