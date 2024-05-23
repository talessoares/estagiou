package com.lab.estagiou.service;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import com.lab.estagiou.model.emailconfirmationtoken.EmailConfirmationTokenEntity;
import com.lab.estagiou.model.emailconfirmationtoken.EmailConfirmationTokenRepository;
import com.lab.estagiou.model.user.UserEntity;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private EmailConfirmationTokenRepository emailConfirmationTokenRepository;

    @Autowired
    private JavaMailSender sender;

    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);

    public ResponseEntity<Object> createConfirmationEmailAndSend(UserEntity user) {
        EmailConfirmationTokenEntity email = createConfirmationEmail(user);
        return sendEmail(email);
    }

    private EmailConfirmationTokenEntity createConfirmationEmail(UserEntity user) {
        String token = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), StandardCharsets.US_ASCII);
        EmailConfirmationTokenEntity emailConfirmationToken = new EmailConfirmationTokenEntity(token, user);

        return emailConfirmationTokenRepository.save(emailConfirmationToken);
    } 

    private ResponseEntity<Object> sendEmail(EmailConfirmationTokenEntity emailConfirmationToken) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(emailConfirmationToken.getUser().getEmail());
            helper.setSubject("Confirm you E-Mail - MFA Application Registration");
            helper.setText("<html>" +
                            "<body>" +
                            "<h2>Dear, </h2>"
                            + "<br/> We're excited to have you get started. " +
                            "Please click on below link to confirm your account."
                            + "<br/> "  + generateConfirmationLink(emailConfirmationToken.getToken())+"" +
                            "<br/> Regards,<br/>" +
                            "MFA Registration team" +
                            "</body>" +
                            "</html>"
                    , true);
            sender.send(message);

            return ResponseEntity.ok().build();
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        
    }

    private String generateConfirmationLink(String token){
        return "<a href=http://localhost:8080/confirm-email?token="+token+">Confirm Email</a>";
    }
    
}
