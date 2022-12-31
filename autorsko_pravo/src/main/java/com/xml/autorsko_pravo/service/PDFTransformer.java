package com.xml.autorsko_pravo.service;

import com.itextpdf.html2pdf.HtmlConverter;

import java.io.File;
import java.io.IOException;

public class PDFTransformer {
    public static final String PDF_FILE = "src/main/resources/templates/a1.pdf";

    public void generatePDF(String documentName) throws IOException {
        new HTMLTransformer().generateHtml(documentName);
        HtmlConverter.convertToPdf(new File(HTMLTransformer.HTML_FILE), new File(PDF_FILE));
    }
}
