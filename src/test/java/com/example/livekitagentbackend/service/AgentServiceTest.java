//package com.example.livekitagentbackend.service;
//
//import com.example.livekitagentbackend.dto.AgentDTO;
//import com.example.livekitagentbackend.entity.Agent;
//import com.example.livekitagentbackend.repository.AgentRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class AgentServiceTest {
//
//    @Mock
//    private AgentRepository agentRepository;
//
//    @InjectMocks
//    private AgentService agentService;
//
//    private AgentDTO sampleDto;
//    private Agent sampleAgent;
//
//    @BeforeEach
//    void setUp() {
//        sampleDto = new AgentDTO();
//        sampleDto.setId(1L);
//        sampleDto.setName("Support Bot");
//        sampleDto.setLanguage("en-US");
//        sampleDto.setVoiceModel("GoogleTTS");
//        sampleDto.setLlmParameters("{\"model\":\"gpt-4\"}");
//
//        sampleAgent = new Agent();
//        sampleAgent.setId(1L);
//        sampleAgent.setName("Support Bot");
//        sampleAgent.setLanguage("en-US");
//        sampleAgent.setVoiceModel("GoogleTTS");
//        sampleAgent.setLlmParameters("{\"model\":\"gpt-4\"}");
//        sampleAgent.setCreatedAt(LocalDateTime.now());
//        sampleAgent.setUpdatedAt(LocalDateTime.now());
//    }
//
//    @Test
//    void shouldCreateOrUpdateAgent() {
//        when(agentRepository.save(any(Agent.class))).thenReturn(sampleAgent);
//
//        agentService.createOrUpdateAgent(sampleDto);
//
//        verify(agentRepository, times(1)).save(any(Agent.class));
//    }
//
//    @Test
//    void shouldReturnAgentById() {
//        when(agentRepository.findById(1L)).thenReturn(Optional.of(sampleAgent));
//
//        AgentDTO result = agentService.getAgent(1L);
//
//        assertNotNull(result);
//        assertEquals("Support Bot", result.getName());
//        assertEquals("en-US", result.getLanguage());
//    }
//
//    @Test
//    void shouldReturnAllAgents() {
//        List<Agent> agents = List.of(sampleAgent);
//        when(agentRepository.findAll()).thenReturn(agents);
//
//        List<AgentDTO> result = agentService.getAllAgents();
//
//        assertEquals(1, result.size());
//        assertEquals("Support Bot", result.get(0).getName());
//    }
//
//    @Test
//    void shouldReturnOptionalAgentById() {
//        when(agentRepository.findById(1L)).thenReturn(Optional.of(sampleAgent));
//
//        Optional<Agent> result = agentService.get(1L);
//
//        assertTrue(result.isPresent());
//        assertEquals("Support Bot", result.get().getName());
//    }
//}
