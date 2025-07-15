package com.example.livekitagentbackend.repository;

import com.example.livekitagentbackend.entites.Agents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agents, Long> {
    Optional<Agents> findByName(String name);


}
