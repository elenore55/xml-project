package com.xml.zig.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceneTakseDTO {
    int osnovnaTaksa;
    int grafickoResenje;
    KlasaDTO klasa;
    int ukupno;

    @Getter
    @Setter
    public static class KlasaDTO {
        String naziv;
        int iznos;
    }
}
