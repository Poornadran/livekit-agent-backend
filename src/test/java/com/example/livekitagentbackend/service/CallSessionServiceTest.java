//package com.example.livekitagentbackend.service;
//
//import com.example.livekitagentbackend.dto.CallSessionDTO;
//import com.example.livekitagentbackend.entity.Agent;
//import com.example.livekitagentbackend.entity.CallSession;
//import com.example.livekitagentbackend.repository.CallSessionRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class CallSessionServiceTest {
//
//    @Mock
//    private CallSessionRepository callSessionRepository;
//
//    @InjectMocks
//    private CallSessionService callSessionService;
//
//    private Agent mockAgent;
//    private CallSession mockSession;
//
//    @BeforeEach
//    void setUp() {
//        mockAgent = new Agent();
//        mockAgent.setId(1L);
//        mockAgent.setName("SupportBot");
//
//        mockSession = new CallSession();
//        mockSession.setId(1L);
//        mockSession.setAgent(mockAgent);
//        mockSession.setModelUsed("gpt-4");
//        mockSession.setStartTime(LocalDateTime.now());
//        mockSession.setStatus("IN_PROGRESS");
//        mockSession.setDynamicVariables("{\"customerName\":\"John\"}");
//        mockSession.setCreatedAt(LocalDateTime.now());
//        mockSession.setUpdatedAt(LocalDateTime.now());
//    }
//
//    @Test
//    void shouldStartCallSession() {
//        when(callSessionRepository.save(any(CallSession.class))).thenReturn(mockSession);
//
//        CallSession result = callSessionService.startSession(mockAgent, "gpt-4", "{\"customerName\":\"John\"}");
//
//        assertNotNull(result);
//        assertEquals("IN_PROGRESS", result.getStatus());
//        assertEquals("gpt-4", result.getModelUsed());
//    }
//
//    @Test
//    void shouldReturnCallSessionDTO() {
//        when(callSessionRepository.findById(1L)).thenReturn(Optional.of(mockSession));
//
//        Optional<CallSessionDTO> result = callSessionService.getSession(1L);
//
//        assertTrue(result.isPresent());
//        assertEquals("IN_PROGRESS", result.get().getStatus());
//        assertEquals(mockSession.getId(), result.get().getId());
//    }
//
//    @Test
//    void shouldUpdateDynamicVariables() {
//        when(callSessionRepository.findById(1L)).thenReturn(Optional.of(mockSession));
//        when(callSessionRepository.save(any(CallSession.class))).thenReturn(mockSession);
//
//        Map<String, Object> newVars = new HashMap<>();
//        newVars.put("orderId", "ORD123");
//
//        callSessionService.updateDynamicVariables(1L, newVars);
//
//        verify(callSessionRepository).save(any(CallSession.class));
//        assertTrue(mockSession.getDynamicVariables().contains("orderId"));
//    }
//
//    @Test
//    void shouldEndCallSession() {
//        when(callSessionRepository.findById(1L)).thenReturn(Optional.of(mockSession));
//        when(callSessionRepository.save(any(CallSession.class))).thenReturn(mockSession);
//
//        callSessionService.endSession(1L);
//
//        assertEquals("ENDED", mockSession.getStatus());
//        assertNotNull(mockSession.getEndTime());
//        verify(callSessionRepository).save(mockSession);
//    }
//}
