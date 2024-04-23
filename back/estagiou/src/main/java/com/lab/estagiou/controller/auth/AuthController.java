package com.lab.estagiou.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.estagiou.controller.dto.request.auth.RequestAuthentication;
import com.lab.estagiou.controller.dto.request.auth.RequestCadastro;
import com.lab.estagiou.service.auth.AuthorizationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
   
    @Autowired
    AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid RequestAuthentication authetinticationDto) {
        return authorizationService.login(authetinticationDto);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register (@RequestBody RequestCadastro registerDto, Authentication authentication) {
        return authorizationService.register(registerDto, authentication);
    }

}
