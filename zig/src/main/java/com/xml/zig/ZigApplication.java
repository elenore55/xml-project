package com.xml.zig;

import com.xml.zig.repository.MetadataExtraction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZigApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ZigApplication.class, args);
        new MetadataExtraction().extract();
    }

}
