package com.lab.estagiou.model.emailconfirmationtoken;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailConfirmationTokenRepository extends JpaRepository<EmailConfirmationTokenEntity, UUID> {

    Optional<EmailConfirmationTokenEntity> findByToken(String token);
    
}
