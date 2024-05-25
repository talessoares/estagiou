package com.lab.estagiou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lab.estagiou.controller.util.UtilController;
import com.lab.estagiou.service.EmailService;

@Controller
@RequestMapping(value = UtilController.API_VERSION, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/confirm-email")
    public ResponseEntity<Object> confirmEmail(@RequestParam String token) {
        return emailService.confirmEmail(token);
    }
    
}
