package com.xml.zig.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OsobaDTO {
    String ime;
    String prezime;
    String poslovnoIme;
    boolean fizickoLice;
    AdresaDTO adresa;
    String telefon;
    String email;
    String faks;
}
