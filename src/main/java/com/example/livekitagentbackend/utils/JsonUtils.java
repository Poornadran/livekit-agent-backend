package com.example.livekitagentbackend.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> fromJson(String json) throws IOException {
        return mapper.readValue(json, new TypeReference<>() {});
    }

    public static String toJson(Map<String, Object> map) throws JsonProcessingException {
        return mapper.writeValueAsString(map);
    }

}
