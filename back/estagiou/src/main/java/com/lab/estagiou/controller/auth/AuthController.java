package com.lab.estagiou.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab.estagiou.controller.dto.request.auth.RequestAuthentication;

import com.lab.estagiou.service.auth.AuthorizationService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/v1/auth")
public class AuthController {
   
    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid RequestAuthentication authetinticationDto) {
        return authorizationService.login(authetinticationDto);
    }

}
