package com.example.livekitagentbackend.service;

import com.example.livekitagentbackend.common.MessagePropertiesService;
import com.example.livekitagentbackend.common.ObjectInvalidException;
import com.example.livekitagentbackend.dtos.AgentDTO;
import com.example.livekitagentbackend.entites.Agents;
import com.example.livekitagentbackend.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private MessagePropertiesService messagePropertiesService;

    public void createOrUpdateAgent(AgentDTO agentDto) {

        if (agentDto.getName() == null || agentDto.getName().trim().isEmpty()) {
            throw new ObjectInvalidException(messagePropertiesService.message("agent.name.required"));
        }

        if (agentDto.getLanguage() == null || agentDto.getLanguage().trim().isEmpty()) {
            throw new ObjectInvalidException(messagePropertiesService.message("agent.language.required"));
        }

        if (agentDto.getVoiceModel() == null || agentDto.getVoiceModel().trim().isEmpty()) {
            throw new ObjectInvalidException(messagePropertiesService.message("agent.voice.model.required"));
        }
        if (agentDto.getLlmParameters() != null && !agentDto.getLlmParameters().trim().isEmpty()) {
            try {
                new com.fasterxml.jackson.databind.ObjectMapper()
                        .readTree(agentDto.getLlmParameters());
            } catch (Exception e) {
                throw new ObjectInvalidException(messagePropertiesService.message("llm.valid.parameter"));
            }
        }

        Optional<Agents> existing = agentRepository.findByName(agentDto.getName());
        if (existing.isPresent() && !existing.get().getId().equals(agentDto.getId())) {
            throw new ObjectInvalidException(messagePropertiesService.message("Agent.name.already.exists"));
        }

        Agents agent = new Agents();
        agent.setId(agentDto.getId());
        agent.setName(agentDto.getName());
        agent.setLanguage(agentDto.getLanguage());
        agent.setVoiceModel(agentDto.getVoiceModel());
        agent.setLlmParameters(agentDto.getLlmParameters());
        agent.setCreatedAt(LocalDateTime.now());
        agentRepository.save(agent);
    }

    public AgentDTO getAgent(Long id) {

        if (id == null) {
            throw new ObjectInvalidException(messagePropertiesService.message("Agent.not.found"));
        }

        Agents agent = agentRepository.findById(id)
                .orElseThrow(() -> new ObjectInvalidException(messagePropertiesService.message("Agent.not.found")));

        return buildAgentObject(agent);
    }

    private AgentDTO buildAgentObject(Agents agent) {
        AgentDTO dto = new AgentDTO();
        dto.setId(agent.getId());
        dto.setName(agent.getName());
        dto.setLanguage(agent.getLanguage());
        dto.setVoiceModel(agent.getVoiceModel());
        dto.setLlmParameters(agent.getLlmParameters());
        dto.setCreatedAt(agent.getCreatedAt());
        dto.setUpdatedAt(agent.getUpdatedAt());
        return dto;
    }

    public List<AgentDTO> getAllAgents() {
        List<Agents> agents = agentRepository.findAll();
        List<AgentDTO> dtoList = new ArrayList<>();
        for (Agents agent : agents) {
            dtoList.add(buildAgentObject(agent));
        }
        return dtoList;
    }


    public Optional<Agents> get(Long id) {
        return agentRepository.findById(id);
    }

}
