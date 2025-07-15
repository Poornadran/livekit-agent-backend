package com.example.livekitagentbackend.service;

import com.example.livekitagentbackend.common.MessagePropertiesService;
import com.example.livekitagentbackend.common.ObjectInvalidException;
import com.example.livekitagentbackend.dtos.CallSessionsDTO;
import com.example.livekitagentbackend.entites.Agents;
import com.example.livekitagentbackend.entites.CallSession;
import com.example.livekitagentbackend.repository.CallSessionRepository;
import com.example.livekitagentbackend.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CallSessionService {

    @Autowired
    private CallSessionRepository callSessionRepository;
    @Autowired
    private MessagePropertiesService messagePropertiesService;

    public CallSession startSession(Agents agent, String modelUsed, String dynamicVarsJson) {
        CallSession session = new CallSession();

        session.setAgent(agent);
        session.setModelUsed(modelUsed);
        session.setStartTime(LocalDateTime.now());
        session.setStatus("IN_PROGRESS");
        session.setDynamicVariables(dynamicVarsJson);
        session.setCreatedAt(LocalDateTime.now());
        session.setUpdatedAt(LocalDateTime.now());

        return callSessionRepository.save(session);
    }

    public CallSessionsDTO getSession(Long id) {
        if (id == null) {
            throw new ObjectInvalidException(messagePropertiesService.message("session.not.found"));
        }

        CallSession session = callSessionRepository.findById(id)
                .orElseThrow(() -> new ObjectInvalidException(messagePropertiesService.message("session.not.found")));

        CallSessionsDTO dto = new CallSessionsDTO();
        dto.setId(session.getId());
        dto.setAgentId(session.getId());
        dto.setStartTime(session.getStartTime());
        dto.setEndTime(session.getEndTime());
        dto.setStatus(session.getStatus());
        dto.setModelUsed(session.getModelUsed());
        dto.setDynamicVariables(session.getDynamicVariables());
        dto.setCreatedAt(session.getCreatedAt());
        dto.setUpdatedAt(session.getUpdatedAt());
        return dto;
    }


    public void updateDynamicVariables(Long sessionId, Map<String, Object> newVars) {

        CallSession session = callSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ObjectInvalidException(messagePropertiesService.message("session.not.found")));

        try {

            Map<String, Object> existingVars = new HashMap<>();

            if (session.getDynamicVariables() != null && !session.getDynamicVariables().isBlank()) {
                existingVars = JsonUtils.fromJson(session.getDynamicVariables());
            }

            existingVars.putAll(newVars);

            session.setDynamicVariables(JsonUtils.toJson(existingVars));
            session.setUpdatedAt(LocalDateTime.now());

            callSessionRepository.save(session);

        } catch (Exception e) {
            throw new ObjectInvalidException(e.getMessage());
        }
    }

    public void endSession(Long sessionId) {

        CallSession session = callSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ObjectInvalidException(messagePropertiesService.message("session.not.found")));
        session.setStatus("ENDED");
        session.setEndTime(LocalDateTime.now());
        session.setUpdatedAt(LocalDateTime.now());

         callSessionRepository.save(session);
    }


}
