package com.lab.estagiou.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lab.estagiou.dto.request.model.RequestRegisterAdmin;
import com.lab.estagiou.dto.response.error.ErrorResponse;
import com.lab.estagiou.model.admin.AdminEntity;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.service.util.UtilUserExists;

@Service
public class AdminService extends UtilUserExists {

    public ResponseEntity<Object> registerAdmin(RequestRegisterAdmin request) {
        if (super.userExists(request)) {
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Email já cadastrado"));
        }

        try {
            UserEntity admin = new AdminEntity(request);
            super.userRepository.save(admin);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }
    
}
