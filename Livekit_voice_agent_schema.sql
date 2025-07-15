
-- Create database
CREATE DATABASE IF NOT EXISTS voice_ai_agent;
USE voice_ai_agent;

-- Table: agents
CREATE TABLE IF NOT EXISTS agents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    language VARCHAR(100),
    voice_model VARCHAR(255),
    llm_parameters TEXT, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table: call_sessions
CREATE TABLE IF NOT EXISTS call_sessions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    agent_id BIGINT NOT NULL,
    start_time DATETIME,
    end_time DATETIME,
    status VARCHAR(50),
    model_used VARCHAR(255),
    dynamic_variables TEXT, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (agent_id) REFERENCES agents(id) ON DELETE CASCADE
);

-- Table: tool_call_logs
CREATE TABLE IF NOT EXISTS tool_call_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    call_session_id BIGINT NOT NULL,
    tool_name VARCHAR(255),
    response_data TEXT, 
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (call_session_id) REFERENCES call_sessions(id) ON DELETE CASCADE
);
