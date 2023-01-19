package com.xml.autorsko_pravo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.xml.autorsko_pravo.dto.CreateZahtevDTO;
import com.xml.autorsko_pravo.model.Zahtev;
import com.xml.autorsko_pravo.service.HTMLTransformer;
import com.xml.autorsko_pravo.service.PDFTransformer;
import com.xml.autorsko_pravo.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import static com.xml.autorsko_pravo.util.Util.PRILOZI_DIR;

@RestController
@RequestMapping(value = "autorsko-pravo/")
public class ZahtevController {

    ZahtevService zahtevService;
    HTMLTransformer htmlTransformer;
    PDFTransformer pdfTransformer;

    @Autowired
    public ZahtevController(ZahtevService zahtevService, HTMLTransformer htmlTransformer, PDFTransformer pdfTransformer) {
        this.zahtevService = zahtevService;
        this.htmlTransformer = htmlTransformer;
        this.pdfTransformer = pdfTransformer;
    }

    @GetMapping(value = "zahtev/{name}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Zahtev> getOne(@PathVariable String name) throws Exception {
        var zahtev = zahtevService.getOne(name);
        if (zahtev == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(zahtev, HttpStatus.OK);
    }

    @GetMapping(value = "zahtev/all", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Zahtev>> getAll() throws Exception {
        return new ResponseEntity<>(zahtevService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "zahtev/html/{name}")
    public ModelAndView getHtml(@PathVariable String name) {
        htmlTransformer.generateHtml(name);
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("a1.html");
        return modelAndView;
    }

    @GetMapping(value = "zahtev/pdf/{name}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPdf(@PathVariable String name) throws IOException {
        pdfTransformer.generatePDF(name);
        byte[] content = Files.readAllBytes(new File(PDFTransformer.PDF_FILE).toPath());
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "a1.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }

    @GetMapping(value = "zahtev/search/{text}/{matchCase}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Zahtev>> search(@PathVariable String text, @PathVariable boolean matchCase) throws Exception {
        return new ResponseEntity<>(zahtevService.search(text, matchCase), HttpStatus.OK);
    }

    @GetMapping(value = "metadata/rdf/{name}")
    public ResponseEntity<Void> extractMetadata(@PathVariable String name) throws Exception {
        zahtevService.extractMetadata(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "metadata/simpleSearch/{name}/{value}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Zahtev>> simpleMetadataSearch(@PathVariable String name, @PathVariable String value) throws Exception {
        return new ResponseEntity<>(zahtevService.simpleMetadataSearch(name, value), HttpStatus.OK);
    }

    @GetMapping(value = "metadata/advancedSearch/{rawInput}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Zahtev>> advancedMetadataSearch(@PathVariable String rawInput) throws Exception {
        return new ResponseEntity<>(zahtevService.advancedMetadataSearch(rawInput), HttpStatus.OK);
    }

    @PostMapping(value = "zahtev/save", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Void> save(@RequestBody CreateZahtevDTO dto) throws Exception {
        zahtevService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "zathev/saveWithPrilog", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> saveWithPrilog(@RequestParam("prilog") MultipartFile prilog, @RequestParam("dto") String dto) throws JsonProcessingException {
        String fileName = UUID.randomUUID() + "-" + prilog.getOriginalFilename();
        var mapper = new XmlMapper();
        var zahtev = mapper.readValue(dto, CreateZahtevDTO.class);
        try {
            var dirPath = Paths.get(PRILOZI_DIR);
            var destinationFile = dirPath.resolve(Paths.get(fileName)).normalize().toAbsolutePath();
            try (var inputStream = prilog.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            zahtevService.save(zahtev, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>(fileName, HttpStatus.OK);
    }

}
