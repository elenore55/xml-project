package com.xml.patent.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PunomocnikDTO {
    String ime;
    String prezime;
    String poslovnoIme;
    boolean fizickoLice;
    String email;
    String telefon;
    AdresaDTO adresa;
    int tipPunomocnika;
}
