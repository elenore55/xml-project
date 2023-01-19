package com.xml.autorsko_pravo.model;

import com.xml.autorsko_pravo.dto.CreateZahtevDTO;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"podnosilac", "autori", "autorskoDelo"})
public class PopunjavaPodnosilac {

    public PopunjavaPodnosilac(CreateZahtevDTO dto) {
        var podnosilacDTO = dto.getPodnosilac();
        if (podnosilacDTO.getTip() == 1)
            this.podnosilac = new Podnosilac.FizickoLice(podnosilacDTO);
        else if (podnosilacDTO.getTip() == 2)
            this.podnosilac = new Podnosilac.PravnoLice(podnosilacDTO);
        else
            this.podnosilac = new Podnosilac.Punomocnik(podnosilacDTO);
        if (dto.getAutori() != null && dto.getAutori().size() > 0) {
            this.autori = new ArrayList<>();
            for (var a : dto.getAutori()) {
                if (a.isZiv())
                    autori.add(new Autor.ZivAutor(a));
                else
                    autori.add(new Autor.PokojniAutor(a));
            }
        }
        if (dto.getAutorskoDelo().isDeloPrerade()) {
            this.autorskoDelo = new AutorskoDelo.DeloPrerade(dto.getAutorskoDelo());
        } else {
            this.autorskoDelo = new AutorskoDelo(dto.getAutorskoDelo());
        }
    }

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
