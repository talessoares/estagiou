package com.lab.estagiou.service.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.lab.estagiou.controller.dto.request.auth.RequestCadastroAdmin;
import com.lab.estagiou.controller.dto.response.error.ErrorResponse;
import com.lab.estagiou.model.entity.Admin;
import com.lab.estagiou.model.entity.Usuario;
import com.lab.estagiou.service.impl.ServiceUsuarioExistsAuthAdmin;

@Service
public class AdminService extends ServiceUsuarioExistsAuthAdmin {

    public ResponseEntity<Object> register(RequestCadastroAdmin request) {
        if (usuarioExists(request)) {
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Email já cadastrado"));
        }

        try {
            Usuario admin = new Admin(request);
            usuarioRepository.save(admin);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }
    
}
