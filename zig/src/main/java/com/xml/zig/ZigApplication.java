package com.xml.zig;

import com.xml.zig.service.Marshalling;
import jakarta.xml.bind.JAXBException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.SAXException;

@SpringBootApplication
public class ZigApplication {

    public static void main(String[] args) throws JAXBException, SAXException {
        SpringApplication.run(ZigApplication.class, args);
        var m = new Marshalling();
        var zahtev = m.unmarshal("z1.xml");
        System.out.println(zahtev);
        m.marshall(zahtev, "z1.xml");
    }

}
