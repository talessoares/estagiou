package com.lab.estagiou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lab.estagiou.exception.generic.NotFoundException;
import com.lab.estagiou.model.emailconfirmationtoken.EmailConfirmationTokenEntity;
import com.lab.estagiou.model.emailconfirmationtoken.EmailConfirmationTokenRepository;
import com.lab.estagiou.model.user.UserEntity;

@Service
public class EmailService {

    @Autowired
    private EmailConfirmationTokenRepository emailConfirmationTokenRepository;

    public ResponseEntity<Object> confirmEmail(String token) {
        EmailConfirmationTokenEntity emailConfirmationToken = emailConfirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException("Token not found: " + token));

        UserEntity user = emailConfirmationToken.getUser();
        user.setEnabled(true);

        emailConfirmationTokenRepository.delete(emailConfirmationToken);

        return ResponseEntity.ok().build();
    }
    
}
