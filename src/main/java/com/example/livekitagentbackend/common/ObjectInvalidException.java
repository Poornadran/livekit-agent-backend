package com.example.livekitagentbackend.common;

public class ObjectInvalidException extends RuntimeException{

    public ObjectInvalidException(String message) {
        super(message);
    }
    public ObjectInvalidException() {
    }
}
