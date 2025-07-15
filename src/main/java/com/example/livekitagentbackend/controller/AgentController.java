package com.example.livekitagentbackend.controller;

import com.example.livekitagentbackend.common.MessagePropertiesService;
import com.example.livekitagentbackend.dtos.AgentDTO;
import com.example.livekitagentbackend.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    private  AgentService agentService;
    @Autowired
    private MessagePropertiesService messagePropertiesService;


    @PutMapping("/createOrUpdate")
    public ResponseEntity<String> createOrUpdate(@RequestBody AgentDTO agentDto) {
        try {
            agentService.createOrUpdateAgent(agentDto);
           String key ;
           key = "agent.save.success";
           if(agentDto.getId()!=null){
               key = "agent.update.success";
           }
            return ResponseEntity.ok(messagePropertiesService.message(key));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }


    @GetMapping("/get/{agentId}")
    public ResponseEntity<?> getById(@PathVariable Long agentId) {
        try {
           AgentDTO agentDTO = agentService.getAgent(agentId);
           return ResponseEntity.ok(agentDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        try {
            List<AgentDTO> agentDTOList = agentService.getAllAgents();
            return ResponseEntity.ok(agentDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }



}
