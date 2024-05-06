package com.lab.estagiou.model.log;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity, UUID> {
    
}
