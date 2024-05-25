package com.lab.estagiou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
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
import com.lab.estagiou.model.log.LogEnum;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.model.user.UserRepository;
import com.lab.estagiou.security.SecurityFilter;
import com.lab.estagiou.security.TokenService;
import com.lab.estagiou.service.util.UtilService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Service
public class AuthorizationService extends UtilService implements UserDetailsService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SecurityFilter securityFilter;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email);
    }

    public ResponseEntity<LoginResponse> login(@RequestBody @Valid RequestAuthentication data) {
        AuthenticationManager authenticationManager = context.getBean(AuthenticationManager.class);

        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        log(LogEnum.INFO, "Login user: " + ((UserEntity) auth.getPrincipal()).getId(), HttpStatus.OK.value());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    public ResponseEntity<Object> logout(HttpServletRequest request) {
        String token = securityFilter.recoverToken(request);
        tokenService.blacklistToken(token);
        
        return ResponseEntity.ok().build();
    }

}