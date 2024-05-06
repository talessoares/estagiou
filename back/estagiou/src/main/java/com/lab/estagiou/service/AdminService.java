package com.lab.estagiou.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lab.estagiou.dto.request.model.RequestRegisterAdmin;
import com.lab.estagiou.dto.response.error.ErrorResponse;
import com.lab.estagiou.model.admin.AdminEntity;
import com.lab.estagiou.model.log.LogEnum;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.service.util.UtilService;

@Service
public class AdminService extends UtilService {

    public ResponseEntity<Object> registerAdmin(RequestRegisterAdmin request) {
        if (super.userExists(request)) {
            logger(LogEnum.WARN, "Email registration attempt: " + request.getEmail());
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Email já cadastrado"));
        }

        UserEntity admin = new AdminEntity(request);
        super.userRepository.save(admin);

        logger(LogEnum.INFO, "Admin registered: " + admin.getId());
        return ResponseEntity.ok().build();
    }
    
}
