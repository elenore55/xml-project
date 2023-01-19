package com.xml.autorsko_pravo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorskoDeloDTO {
    String naslov;
    String altNaslov;
    boolean deloPrerade;
    boolean radniOdnos;
    String nacinKoriscenja;
    String vrsta;
    String formaZapisa;
    IzvornoDeloDTO izvornoDelo;
}
