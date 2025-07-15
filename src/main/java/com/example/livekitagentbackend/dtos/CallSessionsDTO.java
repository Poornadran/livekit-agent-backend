package com.example.livekitagentbackend.dtos;

import java.time.LocalDateTime;

public class CallSessionsDTO {
    private Long id;
    private Long agentId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String modelUsed;
    private String dynamicVariables;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public Long getAgentId() {
        return agentId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getStatus() {
        return status;
    }

    public String getModelUsed() {
        return modelUsed;
    }

    public String getDynamicVariables() {
        return dynamicVariables;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setModelUsed(String modelUsed) {
        this.modelUsed = modelUsed;
    }

    public void setDynamicVariables(String dynamicVariables) {
        this.dynamicVariables = dynamicVariables;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
