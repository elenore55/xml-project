package com.xml.autorsko_pravo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AutorskoPravoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutorskoPravoApplication.class, args);
    }

}
