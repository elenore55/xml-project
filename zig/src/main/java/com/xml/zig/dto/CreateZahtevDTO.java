package com.xml.zig.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateZahtevDTO {
    List<OsobaDTO> podnosiociList;
    OsobaDTO punomocnik;
    OsobaDTO zajednickiPredstavnik;
    PlaceneTakseDTO placeneTakse;
    ZigDTO zig;
    boolean punomocjeRanijePrilozeno;
    boolean punomocjeNaknadnoDostavljeno;
}
