package com.example.livekitagentbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class LivekitAgentBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LivekitAgentBackendApplication.class, args);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages/Message");
        messageSource.setCacheSeconds(10); //reload messages every 10 seconds
        return messageSource;
    }

}
