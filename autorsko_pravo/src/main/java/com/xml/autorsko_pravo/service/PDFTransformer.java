package com.xml.autorsko_pravo.service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.xml.autorsko_pravo.dto.TimePeriodDTO;
import com.xml.autorsko_pravo.model.Resenje;
import com.xml.autorsko_pravo.model.Zahtev;
import com.xml.autorsko_pravo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class PDFTransformer {
    public static final String PDF_FILE = "src/main/resources/templates/a1.pdf";
    public static final String IZVESTAJ_PDF_FILE = "src/main/resources/templates/izvestaj.pdf";
    public static final String RESENJE_PDF_FILE = "src/main/resources/templates/resenje.pdf";
    HTMLTransformer htmlTransformer;

    @Autowired
    public PDFTransformer(HTMLTransformer htmlTransformer) {
        this.htmlTransformer = htmlTransformer;
    }

    public void generatePDF(String documentName) throws IOException {
        htmlTransformer.generateHtml(documentName);
        HtmlConverter.convertToPdf(new File(HTMLTransformer.HTML_FILE), new File(PDF_FILE));
    }

    public void generateIzvestajPDF(TimePeriodDTO dto, int odobreni, int odbijeni, int svi) throws Exception {
        Document document = new Document(PageSize.A4);
        var out = new FileOutputStream(IZVESTAJ_PDF_FILE);
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

    public void generateResenjePDF(Resenje.OdobrenZahtev resenje, Zahtev zahtev) throws Exception {
        Document document = new Document(PageSize.A4);
        var out = new FileOutputStream(RESENJE_PDF_FILE);
        PdfWriter.getInstance(document, out);
        document.open();

        generateCommons(resenje, zahtev, document, "ODOBREN");

        document.close();
    }

    public void generateResenjePDF(Resenje.OdbijenZahtev resenje, Zahtev zahtev) throws Exception {
        Document document = new Document(PageSize.A4);
        var out = new FileOutputStream(RESENJE_PDF_FILE);
        PdfWriter.getInstance(document, out);
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(18);

        generateCommons(resenje, zahtev, document, "ODBIJEN");

        var obraznozenje = new Paragraph(String.format("Obrazloženje: %s", resenje.getObrazlozenje()), font);
        obraznozenje.setSpacingAfter(10f);
        document.add(obraznozenje);

        document.close();
    }

    private void generateCommons(Resenje resenje, Zahtev zahtev, Document document, String resenjeStatus) {
        var boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        boldFont.setSize(18);
        var regularFont = FontFactory.getFont(FontFactory.HELVETICA);
        regularFont.setSize(18);

        var title = new Paragraph("Rešenje zahteva za unošenje u evidenciju i deponovanje autorskog dela", boldFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        title.setSpacingAfter(30f);
        document.add(title);

        var code = new Paragraph(String.format("Broj prijave: A - %d", zahtev.getPopunjavaZavod().getBrojPrijave()), boldFont);
        code.setAlignment(Paragraph.ALIGN_CENTER);
        code.setSpacingAfter(40f);
        document.add(code);

        var status = new Paragraph(String.format("Status: %s", resenjeStatus), boldFont);
        status.setSpacingAfter(10f);
        document.add(status);

        var datumPodnosenja = new Paragraph(String.format("Datum podnošenja: %s", Util.dateToStr(zahtev.getPopunjavaZavod().getDatumPodnosenja())), regularFont);
        datumPodnosenja.setSpacingAfter(10f);
        document.add(datumPodnosenja);

        var datumResenja = new Paragraph(String.format("Datum rešenja: %s", Util.dateToStr(resenje.getDatumResenja())), regularFont);
        datumResenja.setSpacingAfter(10f);
        document.add(datumResenja);

        var sluzbenik = new Paragraph(String.format("Službenik: %s %s", resenje.getImeSluzbenika(), resenje.getPrezimeSluzbenika()), regularFont);
        sluzbenik.setSpacingAfter(10f);
        document.add(sluzbenik);
    }
}
