package com.xml.autorsko_pravo.service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.xml.autorsko_pravo.dto.TimePeriodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class PDFTransformer {
    public static final String PDF_FILE = "src/main/resources/templates/a1.pdf";
    public static final String REPORT_PDF_FILE = "src/main/resources/templates/izvestaj.pdf";
    HTMLTransformer htmlTransformer;

    @Autowired
    public PDFTransformer(HTMLTransformer htmlTransformer) {
        this.htmlTransformer = htmlTransformer;
    }

    public void generatePDF(String documentName) throws IOException {
        htmlTransformer.generateHtml(documentName);
        HtmlConverter.convertToPdf(new File(HTMLTransformer.HTML_FILE), new File(PDF_FILE));
    }

    public byte[] generateReportPDF(TimePeriodDTO dto, int odobreni, int odbijeni, int svi) throws Exception {
        Document document = new Document(PageSize.A4);
        var out = new FileOutputStream(REPORT_PDF_FILE);
        PdfWriter.getInstance(document, out);
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);

        var title = new Paragraph(String.format("Izveštaj za period od %s do %s", dto.getStart(), dto.getEnd()), font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        title.setSpacingAfter(30f);
        document.add(title);

        var table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1f, 1f, 1f});
        table.setSpacingBefore(10f);

        writeTableHeader(table);
        writeTableData(table, odobreni, odbijeni, svi);

        document.add(table);
        document.close();
        return Files.readAllBytes(new File(REPORT_PDF_FILE).toPath());
    }

    private void writeTableHeader(PdfPTable table) {
        var font = FontFactory.getFont(FontFactory.HELVETICA);
        var cell = new PdfPCell();
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Podneti zahtevi #", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Odobreni zahtevi #", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Odbijeni zahtevi #", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table, int odobreni, int odbijeni, int svi) {
        var font = FontFactory.getFont(FontFactory.HELVETICA);
        var cell = new PdfPCell();
        cell.setPadding(5);
        cell.setPhrase(new Phrase(String.valueOf(svi), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(String.valueOf(odobreni), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(String.valueOf(odbijeni), font));
        table.addCell(cell);
    }
}
