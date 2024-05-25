package com.lab.estagiou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.lab.estagiou.model.emailconfirmationtoken.EmailConfirmationTokenEntity;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSendService {

    @Autowired
    private JavaMailSender sender;

    @Async
    public void sendEmailAsync(EmailConfirmationTokenEntity emailConfirmationToken) {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailConfirmationToken.getUser().getEmail());
            helper.setSubject("Confirme seu email - Estagiou");
            helper.setText("<html>" +
                            "<body>" +
                            "<h2>Olá, </h2>"
                            + "<br/> Estamos felizes por ter nos escolhido. " +
                            "Clique no link abaixo para verificar a sua conta!."
                            + "<br/> "  + generateConfirmationLink(emailConfirmationToken.getToken())+"" +
                            "<br/> Anteciosamente,<br/>" +
                            "Time Estagiou" +
                            "</body>" +
                            "</html>"
                    , true);
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
    }

    private String generateConfirmationLink(String token){
        return "<a href=http://localhost:8080/v1/confirm-email?token="+token+">Confirm Email</a>";
    }
    
}
