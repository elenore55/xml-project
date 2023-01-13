package com.xml.zig.repository;

import com.xml.zig.model.Resenje;
import com.xml.zig.model.Zahtev;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;


@Component
public class Marshalling {
    static final String XML_PATH_ZAHTEV = "src/main/resources/xml/";
    static final String XSD_PATH_ZAHTEV = "src/main/resources/xsd/Z1.xsd";
    static final String XSD_PATH_RESENJE = "src/main/resources/xsd/Resenje.xsd";
    static final String CONTEXT_PATH = "com.xml.zig.model";

    SchemaFactory schemaFactory;
    Schema schemaZahtev;
    Schema schemaResenje;
    JAXBContext context;

    public Marshalling() throws SAXException, JAXBException {
        schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        schemaZahtev = schemaFactory.newSchema(new File(XSD_PATH_ZAHTEV));
        schemaResenje = schemaFactory.newSchema(new File(XSD_PATH_RESENJE));
        context = JAXBContext.newInstance(CONTEXT_PATH, Zahtev.class.getClassLoader());
    }

    public Zahtev unmarshalFromFile(String fileName) throws JAXBException {
        var unmarshaller = context.createUnmarshaller();
        unmarshaller.setSchema(schemaZahtev);
        return (Zahtev) unmarshaller.unmarshal(new File(XML_PATH_ZAHTEV + fileName));
    }

    public Zahtev unmarshallContent(Node node) throws JAXBException {
        var unmarshaller = context.createUnmarshaller();
        unmarshaller.setSchema(schemaZahtev);
        return (Zahtev) unmarshaller.unmarshal(node);
    }

    public Resenje unmarshallResenjeContent(Node node) throws JAXBException {
        var unmarshaller = context.createUnmarshaller();
        unmarshaller.setSchema(schemaResenje);
        return (Resenje) unmarshaller.unmarshal(node);
    }

    public void marshallToFile(Zahtev zahtev, String fileName) throws JAXBException {
        var marshaller = context.createMarshaller();
        marshaller.setSchema(schemaZahtev);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(zahtev, new File(XML_PATH_ZAHTEV + fileName));
    }

    public OutputStream marshallToOutputStream(Zahtev zahtev) throws JAXBException {
        var out = new ByteArrayOutputStream();
        var marshaller = context.createMarshaller();
        marshaller.setSchema(schemaZahtev);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(zahtev, out);
        return out;
    }

    public OutputStream marshallToOutputStream(Resenje resenje) throws JAXBException {
        var out = new ByteArrayOutputStream();
        var marshaller = context.createMarshaller();
        marshaller.setSchema(schemaResenje);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(resenje, out);
        return out;
    }

    public InputStream marshallToInputStream(Zahtev zahtev) throws JAXBException {
        var out = new ByteArrayOutputStream();
        var marshaller = context.createMarshaller();
        marshaller.setSchema(schemaZahtev);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(zahtev, out);
        return new ByteArrayInputStream(out.toByteArray());
    }

    public InputStream marshallToInputStream(Resenje resenje) throws JAXBException {
        var out = new ByteArrayOutputStream();
        var marshaller = context.createMarshaller();
        marshaller.setSchema(schemaResenje);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(resenje, out);
        return new ByteArrayInputStream(out.toByteArray());
    }
}
