package com.xml.patent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetadataDTO {

    public enum Type {
        STR, NUM, DATE, BOOL
    }

    public MetadataDTO(String name) {
        this.name = name;
        this.type = Type.STR;
    }

    String name;
    Type type;
}
