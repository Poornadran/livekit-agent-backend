package com.example.livekitagentbackend.entites;

import com.example.livekitagentbackend.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "agents")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agents extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "language")
    private String language;

    @Column(name = "voice_model")
    private String voiceModel;

    @Column(name = "llm_parameters", columnDefinition = "TEXT")
    private String llmParameters;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getVoiceModel() {
        return voiceModel;
    }

    public void setVoiceModel(String voiceModel) {
        this.voiceModel = voiceModel;
    }

    public String getLlmParameters() {
        return llmParameters;
    }

    public void setLlmParameters(String llmParameters) {
        this.llmParameters = llmParameters;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
