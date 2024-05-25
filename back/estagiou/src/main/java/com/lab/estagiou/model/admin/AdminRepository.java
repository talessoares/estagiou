package com.lab.estagiou.model.admin;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, UUID> {
    
}
