package com.xml.patent.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateZahtevDTO {
    String nazivSrpski;
    String nazivEngleski;
    AdresaDTO adresaDostavljanja;
    String vrstaPrijave;
    String datumPrvobitnePrijave;
    int brojPrvobitnePrijave;
    String nacinDostavljanja;
    PodnosilacDTO podnosilac;
    PunomocnikDTO punomocnik;
    PronalazacDTO pronalazac;
    List<RanijaPrijavaDTO> ranijePrijaveList;
}
