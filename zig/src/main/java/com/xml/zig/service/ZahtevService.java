package com.xml.zig.service;

import com.xml.zig.dto.CreateZahtevDTO;
import com.xml.zig.model.*;
import com.xml.zig.repository.Marshalling;
import com.xml.zig.repository.ResenjeRepository;
import com.xml.zig.repository.ZahtevMetadataRepository;
import com.xml.zig.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Year;
import java.util.*;

import static com.xml.zig.util.Util.PRILOZI_DIR;

@Service
public class ZahtevService {

    MetadataSearchService metadataSearchService;
    ZahtevRepository zahtevRepository;
    ZahtevMetadataRepository zahtevMetadataRepository;
    Marshalling marshalling;
    ResenjeRepository resenjeRepository;

    @Autowired
    public ZahtevService(MetadataSearchService metadataSearchService, ZahtevRepository zahtevRepository, Marshalling marshalling,
                         ZahtevMetadataRepository zahtevMetadataRepository,  ResenjeRepository resenjeRepository) {
        this.metadataSearchService = metadataSearchService;
        this.zahtevRepository = zahtevRepository;
        this.zahtevMetadataRepository = zahtevMetadataRepository;
        this.marshalling = marshalling;
        this.resenjeRepository = resenjeRepository;
    }

    public Zahtev getOne(String name) throws Exception {
        return zahtevRepository.get(name);
    }

    public List<Zahtev> getAll() throws Exception {
        var result = zahtevRepository.getAll();
        result.sort(new ZahtevComparator());
        return result;
    }

    public List<Zahtev> search(String text, boolean matchCase) throws Exception {
        var result = zahtevRepository.search(text, matchCase);
        result.sort(new ZahtevComparator());
        return result;
    }

    public void extractMetadata(String name) throws Exception {
        zahtevMetadataRepository.extract(name);
    }

    public List<Zahtev> simpleMetadataSearch(String name, String value) throws Exception {
        return zahtevMetadataRepository.simpleMetadataSearch(name, value);
    }

    public List<Zahtev> advancedMetadataSearch(String rawInput) throws Exception {
        metadataSearchService.generate(rawInput);
        var operators = metadataSearchService.getOperators();
        var statements = metadataSearchService.getStatements();
        var result = zahtevMetadataRepository.advancedMetadataSearch(operators, statements);
        result.sort(new ZahtevComparator());
        return result;
    }

    public void save() throws Exception {
        var zahtev = marshalling.unmarshalFromFile("z1.xml");
        zahtevRepository.save(zahtev);
    }

    public InputStreamResource getAllMetadataAsJSON() throws Exception {
        return zahtevMetadataRepository.getAllAsJSON();
    }

    public InputStreamResource getAllMetadataAsRDF() throws Exception {
        return zahtevMetadataRepository.getAllAsRDF();
    }

    public List<Zahtev> getZahteviBezResenja() throws Exception {
        var reseniZahteviNames = resenjeRepository.getReferences();
        var result = zahtevRepository.getAllExcept(reseniZahteviNames);
        result.sort(new ZahtevComparator());
        return result;
    }

    public List<Zahtev> searchZahteviBezResenja(String text, boolean matchCase) throws Exception {
        var reseniZahteviNames = resenjeRepository.getReferences();
        var zahtevi = zahtevRepository.search(text, matchCase);
        return getBezResenja(reseniZahteviNames, zahtevi);
    }

    private List<Zahtev> getBezResenja(List<String> reseniZahteviNames, List<Zahtev> zahtevi) {
        var result = new ArrayList<Zahtev>();
        for (var z : zahtevi) {
            if (!reseniZahteviNames.contains(String.format("Zahtev%d-%d.xml", z.getPopunjavaZavod().getBrojPrijaveZiga().getId(),
                    z.getPopunjavaZavod().getBrojPrijaveZiga().getGodina()))) {
                result.add(z);
            }
        }
        result.sort(new ZahtevComparator());
        return result;
    }

    public List<Zahtev> searchZahteviBezResenjaByMetadata(String rawInput) throws Exception {
        var reseniZahteviNames = resenjeRepository.getReferences();
        var zahtevi = advancedMetadataSearch(rawInput);
        return getBezResenja(reseniZahteviNames, zahtevi);
    }

    public void save(CreateZahtevDTO dto, Map<String, MultipartFile> files) throws Exception {
        var popunjavaPodnosilac = new PopunjavaPodnosilac(dto);
        var fileNames = new HashMap<String, String>();
        for (var entry : files.entrySet()) {
            fileNames.put(entry.getKey(), saveFile(entry.getValue()));
        }
        var podaciOZigu = popunjavaPodnosilac.getPodaciOZigu();
        podaciOZigu.setIzgled(fileNames.get("izgledZnaka"));
        popunjavaPodnosilac.setPodaciOZigu(podaciOZigu);
        var popunjavaZavod = new PopunjavaZavod();
        var prilozi = new Prilozi(fileNames, dto.isPunomocjeRanijePrilozeno(), dto.isPunomocjeNaknadnoDostavljeno());
        popunjavaZavod.setPrilozi(prilozi);
        popunjavaZavod.setDatumPodnosenja(new Date());
        var brojPrijaveZiga = new PopunjavaZavod.BrojPrijaveZiga();
        brojPrijaveZiga.setGodina(Year.now().getValue());
        brojPrijaveZiga.setId(zahtevRepository.generateNextId());
        popunjavaZavod.setBrojPrijaveZiga(brojPrijaveZiga);
        var zahtev = new Zahtev();
        zahtev.setPopunjavaZavod(popunjavaZavod);
        zahtev.setPopunjavaPodnosilac(popunjavaPodnosilac);
        zahtevRepository.save(zahtev);
        zahtevMetadataRepository.extract(zahtev);
    }

    private String saveFile(MultipartFile file) throws IOException {
        if (file != null) {
            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
            var dirPath = Paths.get(PRILOZI_DIR);
            var destinationFile = dirPath.resolve(Paths.get(fileName)).normalize().toAbsolutePath();
            try (var inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return fileName;
        }
        return null;
    }

    public List<String> getMetadataVariables() {
        return zahtevMetadataRepository.getMetadataVariables();
    }
}
