package com.example.livekitagentbackend.dtos;

import lombok.*;

import java.time.LocalDateTime;



@NoArgsConstructor
public class AgentDTO {

    private Long id;
    private String name;
    private String language;
    private String voiceModel;
    private String llmParameters;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public String getVoiceModel() {
        return voiceModel;
    }

    public String getLlmParameters() {
        return llmParameters;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setVoiceModel(String voiceModel) {
        this.voiceModel = voiceModel;
    }

    public void setLlmParameters(String llmParameters) {
        this.llmParameters = llmParameters;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
