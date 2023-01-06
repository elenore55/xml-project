package com.xml.patent.service;

import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PDFTransformer {
    public static final String PDF_FILE = "src/main/resources/templates/p1.pdf";

    HTMLTransformer htmlTransformer;

    @Autowired
    public PDFTransformer(HTMLTransformer htmlTransformer) {
        this.htmlTransformer = htmlTransformer;
    }

    public void generatePDF(String documentName) throws IOException {
        htmlTransformer.generateHtml(documentName);
        HtmlConverter.convertToPdf(new File(HTMLTransformer.HTML_FILE), new File(PDF_FILE));
    }
}