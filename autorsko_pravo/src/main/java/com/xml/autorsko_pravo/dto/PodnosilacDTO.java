package com.xml.autorsko_pravo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PodnosilacDTO {
    int tip;
    String ime;
    String prezime;
    String poslovnoIme;
    String drzavljanstvo;
    AdresaDTO adresa;
    String telefon;
    String email;
    String pseudonimAutora;
}
