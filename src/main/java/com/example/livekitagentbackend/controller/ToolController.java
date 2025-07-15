package com.example.livekitagentbackend.controller;

import com.example.livekitagentbackend.dtos.ToolsCallLogDTO;
import com.example.livekitagentbackend.entites.ToolCallLog;
import com.example.livekitagentbackend.service.ToolCallLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tools")
public class ToolController {

    @Autowired
    private ToolCallLogService toolCallLogService;

    @PostMapping("/invoke/{toolName}")
    public ResponseEntity<?> invokeTool(
            @PathVariable String toolName,
            @RequestParam Long callSessionId
    ) {
        try {
            ToolCallLog result = toolCallLogService.invokeTool(toolName, callSessionId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/logs/{sessionId}")
    public ResponseEntity<?> getLogs(@PathVariable Long sessionId) {
        try {
            List<ToolsCallLogDTO> toolCallLogDTOList = toolCallLogService.getLogs(sessionId);
            return ResponseEntity.ok(toolCallLogDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
