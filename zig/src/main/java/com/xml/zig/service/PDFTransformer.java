package com.xml.zig.service;

import com.itextpdf.html2pdf.HtmlConverter;

import java.io.File;
import java.io.IOException;

public class PDFTransformer {
    static final String HTML_FILE = "src/main/resources/templates/z1.html";
    public static final String PDF_FILE = "src/main/resources/templates/z1.pdf";

    public void generatePDF(String documentName) throws IOException {
        new HTMLTransformer().generateHtml(documentName);
        HtmlConverter.convertToPdf(new File(HTML_FILE), new File(PDF_FILE));
    }
}
