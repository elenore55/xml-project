package com.xml.patent.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:8082");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        var dir = System.getProperty("user.dir");
        var path = String.format("file:/%s/content/", dir);
        registry.addResourceHandler("/**").addResourceLocations(path);
    }
}
