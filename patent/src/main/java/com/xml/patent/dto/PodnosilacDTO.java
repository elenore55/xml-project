package com.xml.patent.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PodnosilacDTO {

    String ime;
    String prezime;
    String poslovnoIme;
    AdresaDTO adresa;
    boolean pronalazac;
    String drzavljanstvo;
    String email;
    String telefon;
    String faks;
    boolean fizickoLice;
}
