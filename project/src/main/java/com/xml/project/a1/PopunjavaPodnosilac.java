package com.xml.project.a1;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"podnosilac", "autori", "autorskoDelo"})
public class PopunjavaPodnosilac {

    @XmlElement(name = "Podnosilac", required = true)
    Podnosilac podnosilac;

    @XmlElementWrapper(name = "Autori")
    @XmlElement(name = "Autor")
    List<Autor> autori;

    @XmlElement(name = "Autorsko_delo", required = true)
    AutorskoDelo autorskoDelo;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("- Popunjava podnosilac:\n");
        builder.append("\t").append(podnosilac);
        builder.append("\n\t- Autori: ");
        for (var autor : autori) builder.append(autor);
        builder.append("\t").append(autorskoDelo);
        return builder.toString();
    }
}
