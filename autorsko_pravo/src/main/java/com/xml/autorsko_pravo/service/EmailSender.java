package com.xml.autorsko_pravo.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class EmailSender {

    @Configuration
    public static class EmailConfig {

        @Bean
        public ResourceBundleMessageSource emailMessageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename("mailMessages");
            return messageSource;
        }

        @Bean
        public SpringTemplateEngine thymeleafTemplateEngine(ITemplateResolver templateResolver) {
            SpringTemplateEngine templateEngine = new SpringTemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);
            templateEngine.setTemplateEngineMessageSource(emailMessageSource());
            return templateEngine;
        }

        @Bean
        public JavaMailSender getJavaMailSender() {
            var mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);

            mailSender.setUsername("isamrsadventure@gmail.com");
            mailSender.setPassword(new String(Base64.getDecoder().decode("bWlobmJuaWJiaHVrc3pycA=="), StandardCharsets.UTF_8));

            var props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            return mailSender;
        }
    }
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public EmailSender(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void send(String to, EmailMessage emailMessage) {
        try {
            var message = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("Zavod", "Zavod");
            helper.setTo(to);
            helper.setSubject(emailMessage.getSubject());
            helper.setText(emailMessage.getHtmlBody(templateEngine), true);
            helper.addAttachment("resenje.pdf", new File(PDFTransformer.RESENJE_PDF_FILE));
            mailSender.send(message);
            System.out.println("Message sent");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
