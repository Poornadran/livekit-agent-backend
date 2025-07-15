package com.example.livekitagentbackend.dtos;

import java.time.LocalDateTime;

public class ToolsCallLogDTO {
    private Long id;
    private Long callSessionId;
    private String toolName;
    private String responseData;
    private LocalDateTime timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCallSessionId() {
        return callSessionId;
    }

    public void setCallSessionId(Long callSessionId) {
        this.callSessionId = callSessionId;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
