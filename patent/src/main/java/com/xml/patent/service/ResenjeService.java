package com.xml.patent.service;

import com.xml.patent.dto.CreateResenjeDTO;
import com.xml.patent.model.Resenje;
import com.xml.patent.repository.ResenjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResenjeService {

    ResenjeRepository resenjeRepository;

    @Autowired
    public ResenjeService(ResenjeRepository resenjeRepository) {
        this.resenjeRepository = resenjeRepository;
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
}
