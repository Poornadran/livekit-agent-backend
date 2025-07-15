package com.example.livekitagentbackend.repository;

import com.example.livekitagentbackend.entites.ToolCallLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToolCallLogRepository extends JpaRepository<ToolCallLog, Long> {

    List<ToolCallLog> findByCallSession_Id(Long callSessionId);

}
