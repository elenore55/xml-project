package com.xml.autorsko_pravo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateZahtevDTO {

    PodnosilacDTO podnosilac;
    AutorskoDeloDTO autorskoDelo;
    List<AutorDTO> autori;
    String opisDela;
}
