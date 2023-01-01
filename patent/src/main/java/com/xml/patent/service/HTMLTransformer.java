package com.xml.patent.service;

import com.xml.patent.repository.ZahtevRepository;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;

public class HTMLTransformer {

    static final DocumentBuilderFactory documentFactory;

    static final TransformerFactory transformerFactory;

    ZahtevRepository zahtevRepository;

    static final String XSL_FILE = "src/main/resources/xslt/p1.xsl";

    public static final String HTML_FILE = "src/main/resources/templates/p1.html";

    static {
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);
        transformerFactory = TransformerFactory.newInstance();
    }

    public void generateHtml(String documentName) {
        try {
            zahtevRepository = new ZahtevRepository();
            var transformSource = new StreamSource(new File(XSL_FILE));
            var transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");
            var source = new DOMSource(zahtevRepository.getAsNode(documentName));
            var result = new StreamResult(new FileOutputStream(HTML_FILE));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

