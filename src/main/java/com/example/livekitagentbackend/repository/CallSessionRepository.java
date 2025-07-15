package com.example.livekitagentbackend.repository;

import com.example.livekitagentbackend.entites.CallSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallSessionRepository  extends JpaRepository<CallSession, Long> {
}
