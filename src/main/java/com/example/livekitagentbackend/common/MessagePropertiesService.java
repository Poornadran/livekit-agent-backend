package com.example.livekitagentbackend.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessagePropertiesService {

    @Autowired
    private MessageSource messageSource;

    public String message(String key){
        return messageSource.getMessage(key,null, Locale.ENGLISH);
    }
}
