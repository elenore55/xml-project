package com.xml.patent.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateResenjeDTO {

    protected String imeSluzbenika;
    protected String prezimeSluzbenika;
    protected String nazivDokumenta;

    @Getter
    @Setter
    public static class CreateOdbijenZahtevDTO extends CreateResenjeDTO {
        String obrazlozenje;
    }
}
