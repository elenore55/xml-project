package com.xml.autorsko_pravo;

import com.xml.autorsko_pravo.service.Marshalling;
import jakarta.xml.bind.JAXBException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.SAXException;

@SpringBootApplication
public class AutorskoPravoApplication {

    public static void main(String[] args) throws JAXBException, SAXException {
        SpringApplication.run(AutorskoPravoApplication.class, args);
        var m = new Marshalling();
        var zahtev = m.unmarshal("a1.xml");
        System.out.println(zahtev);
        m.marshall(zahtev, "a1.xml");
    }

}
