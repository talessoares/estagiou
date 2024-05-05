package com.lab.estagiou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.lab.estagiou.dto.request.auth.RequestAuthentication;
import com.lab.estagiou.dto.response.auth.LoginResponse;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.model.user.UserRepository;
import com.lab.estagiou.security.TokenService;

import jakarta.validation.Valid;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email);
    }

    public ResponseEntity<Object> login(@RequestBody @Valid RequestAuthentication data) {
        AuthenticationManager authenticationManager = context.getBean(AuthenticationManager.class);

        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(),
                data.getPassword());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }

}