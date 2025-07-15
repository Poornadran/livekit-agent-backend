package com.example.livekitagentbackend.entites;

import com.example.livekitagentbackend.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tool_call_logs")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToolCallLog extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "call_session_id", nullable = false)
    private CallSession callSession;

    @Column(name = "tool_name")
    private String toolName;

    @Column(name = "response_data", columnDefinition = "TEXT")
    private String responseData;

    private LocalDateTime timestamp = LocalDateTime.now();

    public CallSession getCallSession() {
        return callSession;
    }

    public void setCallSession(CallSession callSession) {
        this.callSession = callSession;
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
