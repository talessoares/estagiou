package com.lab.estagiou.model.email;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailConfirmationTokenRepository extends JpaRepository<EmailConfirmationTokenEntity, UUID> {

    EmailConfirmationTokenEntity findByToken(String token);
    String deleteByToken(String token);
    
}
