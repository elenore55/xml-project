package com.xml.users.repository;

import com.xml.users.model.Korisnik;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import javax.xml.XMLConstants;
import javax.xml.validation.SchemaFactory;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

@Component
public class Marshalling {

    static final String CONTEXT_PATH = "com.xml.users.model";

    SchemaFactory schemaFactory;
    JAXBContext context;

    public Marshalling() throws JAXBException {
        schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        context = JAXBContext.newInstance(CONTEXT_PATH, Korisnik.class.getClassLoader());
    }

    public OutputStream marshallToOutputStream(Korisnik korisnik) throws JAXBException {
        var out = new ByteArrayOutputStream();
        var marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(korisnik, out);
        return out;
    }

    public Korisnik unmarshallContent(Node node) throws JAXBException {
        var unmarshaller = context.createUnmarshaller();
        return (Korisnik) unmarshaller.unmarshal(node);
    }
}
