package com.xml.patent.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PronalazacDTO {
    String ime;
    String prezime;
    boolean anoniman;
    String telefon;
    String email;
    String faks;
    AdresaDTO adresa;
}
