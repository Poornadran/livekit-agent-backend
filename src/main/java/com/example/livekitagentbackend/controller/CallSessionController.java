package com.example.livekitagentbackend.controller;

import com.example.livekitagentbackend.common.MessagePropertiesService;
import com.example.livekitagentbackend.common.ObjectInvalidException;
import com.example.livekitagentbackend.dtos.CallSessionsDTO;
import com.example.livekitagentbackend.entites.Agents;
import com.example.livekitagentbackend.entites.CallSession;
import com.example.livekitagentbackend.service.AgentService;
import com.example.livekitagentbackend.service.CallSessionService;
import com.example.livekitagentbackend.service.LiveKitService;
import com.example.livekitagentbackend.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/calls")
public class CallSessionController {

    @Autowired
    private CallSessionService callSessionService;
    @Autowired
    private AgentService agentService;
    @Autowired
    private LiveKitService liveKitService;
    @Autowired
    private MessagePropertiesService messagePropertiesService;

    @PostMapping("/start")
    public ResponseEntity<Map<String, Object>> startCall(
            @RequestParam Long agentId,
            @RequestParam String modelUsed,
            @RequestBody(required = false) Map<String, Object> dynamicVars
    ) {

        Agents agent = agentService.get(agentId)
                .orElseThrow(() ->  new ObjectInvalidException(messagePropertiesService.message("Agent.not.found")));

        String roomName = "session-" + UUID.randomUUID();
        String identity = "agent-" + agentId;

        liveKitService.createRoomIfNotExists(roomName);

        String token = liveKitService.generateAccessToken(identity, roomName);

        String dynamicJson = "{}";
        if (dynamicVars != null && !dynamicVars.isEmpty()) {
            try {
                dynamicJson = JsonUtils.toJson(dynamicVars);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid dynamic variables"));
            }
        }

        CallSession session = callSessionService.startSession(agent, modelUsed, dynamicJson);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("roomName", roomName);
        response.put("sessionId", session.getId());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/end/{id}")
    public ResponseEntity<?> endCall(@PathVariable Long id) {
        try {
            callSessionService.endSession(id);
            return ResponseEntity.ok(messagePropertiesService.message("call.ended.success"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            CallSessionsDTO callSessionDTO = callSessionService.getSession(id);
            return ResponseEntity.ok(callSessionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/update-dynamicVariable/{sessionId}")
    public ResponseEntity<?> updateVariables(
            @PathVariable Long sessionId,
            @RequestBody Map<String, Object> variables
    ) {
        try {
            callSessionService.updateDynamicVariables(sessionId, variables);
            return ResponseEntity.ok(messagePropertiesService.message("variables.updated.success"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }


}



