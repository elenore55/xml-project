package com.xml.autorsko_pravo.service;

import lombok.Setter;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Map;

@Setter
public class EmailMessage {
    Map<String, Object> templateMap;
    String template = "resenje-template.html";

    public EmailMessage(String status) {
        templateMap = Map.of("status", status);
    }
    public String getHtmlBody(SpringTemplateEngine thymeleafTemplateEngine) {
        var thymeleafContext = new Context();
        thymeleafContext.setVariables(templateMap);
        return thymeleafTemplateEngine.process(template, thymeleafContext);
    }

    public String getSubject() {
        return "Rešenje zahteva";
    }
}
