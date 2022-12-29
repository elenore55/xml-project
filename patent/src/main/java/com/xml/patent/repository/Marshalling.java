package com.xml.patent.repository;

import com.xml.patent.model.Zahtev;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

public class Marshalling {
    static final String XML_PATH = "src/main/resources/xml/";
    static final String XSD_PATH = "src/main/resources/xsd/P1.xsd";
    static final String CONTEXT_PATH = "com.xml.patent.model";

    SchemaFactory schemaFactory;
    Schema schema;
    JAXBContext context;

    public Marshalling() throws SAXException, JAXBException {
        schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        schema = schemaFactory.newSchema(new File(XSD_PATH));
        context = JAXBContext.newInstance(CONTEXT_PATH, Zahtev.class.getClassLoader());
    }

    public Zahtev unmarshalFromFile(String fileName) throws JAXBException {
        var unmarshaller = context.createUnmarshaller();
        unmarshaller.setSchema(schema);
        return (Zahtev) unmarshaller.unmarshal(new File(XML_PATH + fileName));
    }

    public Zahtev unmarshallContent(Node node) throws JAXBException {
        var unmarshaller = context.createUnmarshaller();
        unmarshaller.setSchema(schema);
        return (Zahtev) unmarshaller.unmarshal(node);
    }

    public void marshallToFile(Zahtev zahtev, String fileName) throws JAXBException {
        var marshaller = context.createMarshaller();
        marshaller.setSchema(schema);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(zahtev, new File(XML_PATH + fileName));
    }

    public OutputStream marshallToOutputStream(Zahtev zahtev) throws JAXBException {
        var out = new ByteArrayOutputStream();
        var marshaller = context.createMarshaller();
        marshaller.setSchema(schema);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(zahtev, out);
        return out;
    }
}
