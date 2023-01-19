package com.xml.autorsko_pravo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorDTO {
    String ime;
    String prezime;
    boolean ziv;
    int godinaSmrti;
    AdresaDTO adresa;
    String drzavljanstvo;
    String pseudonim;
}
