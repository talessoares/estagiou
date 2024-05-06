package com.lab.estagiou.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lab.estagiou.dto.request.model.RequestRegisterAdmin;
import com.lab.estagiou.exception.generic.EmailAlreadyRegisteredException;
import com.lab.estagiou.model.admin.AdminEntity;
import com.lab.estagiou.model.log.LogEnum;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.service.util.UtilService;


@Service
public class AdminService extends UtilService {

    public ResponseEntity<Object> registerAdmin(RequestRegisterAdmin request) {
        if (super.userExists(request)) {
            throw new EmailAlreadyRegisteredException("Email registration attempt: " + request.getEmail());
        }

        UserEntity admin = new AdminEntity(request);
        super.userRepository.save(admin);

        logger(LogEnum.INFO, "Admin registered: " + admin.getId());
        return ResponseEntity.ok().build();
    }
    
}
