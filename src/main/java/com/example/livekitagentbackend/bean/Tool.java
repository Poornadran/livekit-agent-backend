package com.example.livekitagentbackend.bean;

import java.util.Map;

public interface Tool {

    String getName();
    String invoke(Map<String, Object> context);

}
