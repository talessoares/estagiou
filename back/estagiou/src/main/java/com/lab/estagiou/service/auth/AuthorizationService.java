package com.lab.estagiou.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.lab.estagiou.controller.dto.request.auth.RequestCadastro;
import com.lab.estagiou.controller.dto.request.auth.RequestAuthentication;
import com.lab.estagiou.controller.dto.response.auth.LoginResponse;
import com.lab.estagiou.controller.dto.response.error.ErrorResponse;
import com.lab.estagiou.model.entity.Admin;
import com.lab.estagiou.model.entity.Aluno;
import com.lab.estagiou.model.entity.Empresa;
import com.lab.estagiou.model.entity.Usuario;
import com.lab.estagiou.model.repository.EmpresaRepository;
import com.lab.estagiou.model.repository.UsuarioRepository;
import com.lab.estagiou.security.TokenService;

import jakarta.validation.Valid;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email);
    }

    public ResponseEntity<Object> login(@RequestBody @Valid RequestAuthentication data) {
        AuthenticationManager authenticationManager = context.getBean(AuthenticationManager.class);

        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(),
                data.getSenha());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    public ResponseEntity<Object> register(@RequestBody RequestCadastro registerDto, Authentication authentication) {
        if (usuarioRepository.findByEmail(registerDto.getEmail()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String senhaEncriptada = new BCryptPasswordEncoder().encode(registerDto.getSenha());

        try {
            registerDto.validar();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }

        try {
            Usuario usuario = createUser(registerDto, senhaEncriptada, authentication);
            
            if (usuario != null) {
                usuarioRepository.save(usuario);
                return ResponseEntity.ok().build();
            } 

            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    private Usuario createUser(RequestCadastro registerDto, String senhaEncriptada, Authentication authentication) {
        switch (registerDto.getRole()) {
            case "ALUNO":
                return new Aluno(registerDto, senhaEncriptada);
            case "EMPRESA":
                if (authentication.getPrincipal() instanceof Admin
                        && !empresaRepository.existsByCnpj(registerDto.getCnpj())) {
                    return new Empresa(registerDto, senhaEncriptada);
                }
                break;
            case "ADMIN":
                if (authentication.getPrincipal() instanceof Admin) {
                    return new Admin(registerDto, senhaEncriptada);
                }
                break;
            default:
                break;
        }
        return null;
    }

}