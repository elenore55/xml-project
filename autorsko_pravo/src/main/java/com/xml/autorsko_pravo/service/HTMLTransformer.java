package com.xml.autorsko_pravo.service;

import com.xml.autorsko_pravo.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;


@Service
public class HTMLTransformer {

    static final DocumentBuilderFactory documentFactory;
    static final TransformerFactory transformerFactory;
    static final String XSL_FILE = "src/main/resources/xslt/a1.xsl";
    public static final String HTML_FILE = "src/main/resources/templates/a1.html";

    ZahtevRepository zahtevRepository;

    static {
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);
        transformerFactory = TransformerFactory.newInstance();
    }

    @Autowired
    public HTMLTransformer(ZahtevRepository zahtevRepository) {
        this.zahtevRepository = zahtevRepository;
    }

    public void generateHtml(String documentName) {
        generateHtml(documentName, HTML_FILE);
    }

    public void generateHtml(String documentName, String path) {
        try {
            var transformSource = new StreamSource(new File(XSL_FILE));
            var transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");
            var source = new DOMSource(zahtevRepository.getAsNode(documentName));
            var result = new StreamResult(new FileOutputStream(path));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
