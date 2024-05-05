package com.lab.estagiou.service.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.lab.estagiou.dto.request.model.util.RequestRegister;
import com.lab.estagiou.model.user.UserRepository;

public abstract class UtilUserExists {

    @Autowired
    public UserRepository userRepository;

    public boolean userExists(RequestRegister request) {
        return userRepository.findByEmail(request.getEmail()) != null;
    }
    
}
