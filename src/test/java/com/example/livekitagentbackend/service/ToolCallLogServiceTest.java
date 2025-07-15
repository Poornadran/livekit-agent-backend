//package com.example.livekitagentbackend.service;
//
//import com.example.livekitagentbackend.dto.ToolCallLogDTO;
//import com.example.livekitagentbackend.entity.CallSession;
//import com.example.livekitagentbackend.entity.ToolCallLog;
//import com.example.livekitagentbackend.repository.CallSessionRepository;
//import com.example.livekitagentbackend.repository.ToolCallLogRepository;
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
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class ToolCallLogServiceTest {
//
//    @Mock
//    private CallSessionRepository callSessionRepository;
//
//    @Mock
//    private ToolCallLogRepository toolCallLogRepository;
//
//    @InjectMocks
//    private ToolCallLogService toolCallLogService;
//
//    private CallSession mockSession;
//
//    @BeforeEach
//    void setUp() {
//        mockSession = new CallSession();
//        mockSession.setId(1L);
//        mockSession.setDynamicVariables("{\"customerName\":\"Priya\",\"orderId\":\"ORD123\"}");
//    }
//
//    @Test
//    void shouldInvokeToolAndLogResponse() {
//        String toolName = "account_balance";
//
//        when(callSessionRepository.findById(1L)).thenReturn(Optional.of(mockSession));
//
//        ToolCallLog savedLog = new ToolCallLog();
//        savedLog.setToolName(toolName);
//        savedLog.setResponseData("Account balance for Priya is ₹8,400");
//        savedLog.setCallSession(mockSession);
//        savedLog.setTimestamp(LocalDateTime.now());
//
//        when(toolCallLogRepository.save(any(ToolCallLog.class))).thenReturn(savedLog);
//
//        ToolCallLog result = toolCallLogService.invokeTool(toolName, 1L);
//
//        assertNotNull(result);
//        assertEquals("account_balance", result.getToolName());
//        assertTrue(result.getResponseData().contains("Priya"));
//        verify(toolCallLogRepository).save(any(ToolCallLog.class));
//    }
//
//    @Test
//    void shouldReturnLogsAsDTOList() {
//        ToolCallLog log = new ToolCallLog();
//        log.setId(101L);
//        log.setToolName("order_status");
//        log.setResponseData("Order ORD123 is out for delivery");
//        log.setCallSession(mockSession);
//        log.setTimestamp(LocalDateTime.now());
//
//        when(toolCallLogRepository.findByCallSession_Id(1L)).thenReturn(List.of(log));
//
//        List<ToolCallLogDTO> result = toolCallLogService.getLogs(1L);
//
//        assertEquals(1, result.size());
//        assertEquals("order_status", result.get(0).getToolName());
//        assertTrue(result.get(0).getResponseData().contains("ORD123"));
//        assertEquals(101L, result.get(0).getId());
//    }
//}
