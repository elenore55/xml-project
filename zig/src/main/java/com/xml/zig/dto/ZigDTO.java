package com.xml.zig.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ZigDTO {
    String tipZiga;
    String vrstaZnaka;
    String transliteracija;
    String prevod;
    String opis;
    List<String> bojeList;
    List<Integer> nicanskaKlasifikacijaList;
    String pravoPrvenstva;
    Object izgledZnaka;
}
