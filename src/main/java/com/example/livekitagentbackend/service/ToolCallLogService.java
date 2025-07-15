package com.example.livekitagentbackend.service;

import com.example.livekitagentbackend.common.MessagePropertiesService;
import com.example.livekitagentbackend.common.ObjectInvalidException;
import com.example.livekitagentbackend.dtos.ToolsCallLogDTO;
import com.example.livekitagentbackend.entites.CallSession;
import com.example.livekitagentbackend.entites.ToolCallLog;
import com.example.livekitagentbackend.repository.CallSessionRepository;
import com.example.livekitagentbackend.repository.ToolCallLogRepository;
import com.example.livekitagentbackend.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ToolCallLogService {

    @Autowired
    private CallSessionRepository callSessionRepository;
    @Autowired
    private ToolCallLogRepository toolCallLogRepository;
    @Autowired
    private MessagePropertiesService messagePropertiesService;

    public ToolCallLog invokeTool(String toolName, Long sessionId) {

        CallSession session = callSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ObjectInvalidException(messagePropertiesService.message("session.not.found")));

        String response = simulateTool(toolName, session);

        ToolCallLog log = new ToolCallLog();
        log.setToolName(toolName);
        log.setCallSession(session);
        log.setResponseData(response);
        log.setTimestamp(LocalDateTime.now());

        return toolCallLogRepository.save(log);
    }

    private String simulateTool(String toolName, CallSession session) {
        Map<String, Object> vars = new HashMap<>();
        if (session.getDynamicVariables() != null) {
            try {
                vars = JsonUtils.fromJson(session.getDynamicVariables());
            } catch (Exception ignored) {}
        }

        return switch (toolName) {
            case "account_balance" -> {
                String name = (String) vars.getOrDefault("customerName", "customer");
                yield "Account balance for " + name + " is ₹8,400";
            }
            case "order_status" -> {
                String orderId = (String) vars.getOrDefault("orderId", "unknown");
                yield "Order " + orderId + " is out for delivery";
            }
            default -> "Tool '" + toolName + "' not recognized";
        };
    }

    public List<ToolsCallLogDTO> getLogs(Long sessionId) {

        if (sessionId == null) {
            throw new ObjectInvalidException(messagePropertiesService.message("session.not.found"));
        }

        List<ToolCallLog> logs = toolCallLogRepository.findByCallSession_Id(sessionId);

        if(logs.isEmpty()){
            throw new ObjectInvalidException(messagePropertiesService.message("session.details.not.found"));
        }

        List<ToolsCallLogDTO> dtoList = new ArrayList<>();
        for (ToolCallLog log : logs) {
            ToolsCallLogDTO dto = new ToolsCallLogDTO();
            dto.setId(log.getId());
            dto.setCallSessionId(log.getCallSession().getId());
            dto.setToolName(log.getToolName());
            dto.setResponseData(log.getResponseData());
            dto.setTimestamp(log.getTimestamp());
            dtoList.add(dto);
        }

        return dtoList;
    }




}
