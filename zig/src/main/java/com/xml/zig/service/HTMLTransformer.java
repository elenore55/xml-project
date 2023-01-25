package com.xml.zig.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.xml.zig.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class HTMLTransformer {

    static final DocumentBuilderFactory documentFactory;
    static final TransformerFactory transformerFactory;
    static final String XSL_FILE = "src/main/resources/xslt/z1.xsl";
    public static final String HTML_FILE = "src/main/resources/templates/z1.html";

    public static final String QR_CODE_PATH = "content/qr_codes/";
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

    public void generateHtml(String documentName) throws Exception {
        generateQR(documentName);

        try {
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

    private void generateQR(String documentName) throws WriterException, IOException {
        File directory = new File(QR_CODE_PATH);
        if (! directory.exists()){
            var b = directory.mkdirs();
            System.out.println(b);
        }
        var qrCodeWriter = new QRCodeWriter();
        var bitMatrix = qrCodeWriter.encode("http://localhost:8003/zig/zahtev/htmlString/" + documentName, BarcodeFormat.QR_CODE, 200, 200);
        var withoutExtension = documentName.substring(0, documentName.indexOf('.'));
        var file = new File(String.format("%s/%s.jpg", QR_CODE_PATH, withoutExtension));
        ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), "png", file);
    }

}
