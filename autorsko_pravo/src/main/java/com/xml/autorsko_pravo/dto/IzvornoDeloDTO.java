package com.xml.autorsko_pravo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IzvornoDeloDTO {
    String naslov;
    String altNaslov;
    List<AutorDTO> autori;
}
