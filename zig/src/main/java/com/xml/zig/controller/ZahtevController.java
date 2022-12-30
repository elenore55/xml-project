package com.xml.zig.controller;

import com.xml.zig.model.Zahtev;
import com.xml.zig.repository.ZahtevRepository;
import com.xml.zig.service.HTMLTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "zig/")
public class ZahtevController {

    @GetMapping(value = "zahtev/{name}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Zahtev> getOne(@PathVariable String name) throws Exception {
        var repository = new ZahtevRepository();
        var zahtev = repository.get(name);
        if (zahtev == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(zahtev, HttpStatus.OK);
    }

    @GetMapping(value = "zahtev/all", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Zahtev>> getAll() throws Exception {
        var repository = new ZahtevRepository();
        return new ResponseEntity<>(repository.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "zahtev/html/{name}")
    public ModelAndView getHtml(@PathVariable String name) {
        var transform = new HTMLTransformer();
        transform.transformToHtml(name);
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("z1.html");
        return modelAndView;
    }
}
