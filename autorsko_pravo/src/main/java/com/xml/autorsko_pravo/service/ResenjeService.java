package com.xml.autorsko_pravo.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.xml.autorsko_pravo.dto.CreateResenjeDTO;
import com.xml.autorsko_pravo.dto.TimePeriodDTO;
import com.xml.autorsko_pravo.model.Resenje;
import com.xml.autorsko_pravo.repository.ResenjeMetadataRepository;
import com.xml.autorsko_pravo.repository.ResenjeRepository;
import com.xml.autorsko_pravo.repository.ZahtevMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

@Service
public class ResenjeService {

    MetadataSearchService metadataSearchService;
    ResenjeRepository resenjeRepository;
    ResenjeMetadataRepository resenjeMetadataRepository;
    ZahtevMetadataRepository zahtevMetadataRepository;

    @Autowired
    public ResenjeService(MetadataSearchService metadataSearchService, ResenjeRepository resenjeRepository,
                          ResenjeMetadataRepository resenjeMetadataRepository, ZahtevMetadataRepository zahtevMetadataRepository) {
        this.metadataSearchService = metadataSearchService;
        this.resenjeRepository = resenjeRepository;
        this.resenjeMetadataRepository = resenjeMetadataRepository;
        this.zahtevMetadataRepository = zahtevMetadataRepository;
    }

    public void accept(CreateResenjeDTO dto) throws Exception {
        var resenje = new Resenje.OdobrenZahtev();
        resenje.setImeSluzbenika(dto.getImeSluzbenika());
        resenje.setPrezimeSluzbenika(dto.getPrezimeSluzbenika());
        resenje.setDatumResenja(new Date());
        resenje.setSifra(dto.getNazivDokumenta());
        resenje.setReferenca(dto.getNazivDokumenta());
        resenjeRepository.save(resenje);
    }

    public void reject(CreateResenjeDTO.CreateOdbijenZahtevDTO dto) throws Exception {
        var resenje = new Resenje.OdbijenZahtev();
        resenje.setImeSluzbenika(dto.getImeSluzbenika());
        resenje.setPrezimeSluzbenika(dto.getPrezimeSluzbenika());
        resenje.setDatumResenja(new Date());
        resenje.setObrazlozenje(dto.getObrazlozenje());
        resenje.setReferenca(dto.getNazivDokumenta());
        resenjeRepository.save(resenje);
    }

    public Resenje getOne(String name) throws Exception {
        return resenjeRepository.get(name);
    }

    public List<Resenje> getAll() throws Exception {
        return resenjeRepository.getAll();
    }

    public List<Resenje> search(String text, boolean matchCase) throws Exception {
        return resenjeRepository.search(text, matchCase);
    }

    public void extractMetadata(String name) throws Exception {
        resenjeMetadataRepository.extract(name);
    }

    public List<Resenje> simpleMetadataSearch(String name, String value) throws Exception {
        return resenjeMetadataRepository.simpleMetadataSearch(name, value);
    }

    public List<Resenje> advancedMetadataSearch(String rawInput) throws Exception {
        metadataSearchService.generate(rawInput);
        var operators = metadataSearchService.getOperators();
        var statements = metadataSearchService.getStatements();
        return resenjeMetadataRepository.advancedMetadataSearch(operators, statements);
    }

    public byte[] generateReport(TimePeriodDTO dto) throws Exception {
        int odobreni = resenjeMetadataRepository.countOdobreniZahtevi(dto.getStart(), dto.getEnd());
        int odbijeni = resenjeMetadataRepository.countOdbijeniZahtevi(dto.getStart(), dto.getEnd());
        int svi = zahtevMetadataRepository.countZahtevi(dto.getStart(), dto.getEnd());
        String filePath = "src/main/resources/templates/izvestaj.pdf";

        Document document = new Document(PageSize.A4);
        var out = new FileOutputStream(filePath);
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
        return Files.readAllBytes(new File(filePath).toPath());
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
