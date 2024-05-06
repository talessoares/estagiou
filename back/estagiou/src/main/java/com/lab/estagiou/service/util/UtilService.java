package com.lab.estagiou.service.util;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import com.lab.estagiou.dto.request.model.util.RequestRegister;
import com.lab.estagiou.exception.generic.UnauthorizedUserException;
import com.lab.estagiou.exception.handler.util.HandlerExceptionUtil;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.model.user.UserRepository;
import com.lab.estagiou.model.user.UserRoleEnum;

public abstract class UtilService extends HandlerExceptionUtil {

    @Autowired
    public UserRepository userRepository;

    public boolean userExists(RequestRegister request) {
        return userRepository.findByEmail(request.getEmail()) != null;
    }

    public void verifyAuthorization(Authentication authentication, UUID id) {
        if (!userIsSameOrAdmin(authentication, id)){
            if (authentication == null) {
                throw new UnauthorizedUserException("Unauthorized access attempt");
            }
            throw new UnauthorizedUserException("Unauthorized access attempt: " + ((UserEntity) authentication.getPrincipal()).getId());
        }
    }

    private boolean userIsSameOrAdmin(Authentication authentication, UUID id) {
        if (authentication == null) {
            return false;
        }

        UserEntity user = (UserEntity) authentication.getPrincipal();
        return user.getId().equals(id) || user.getRole().equals(UserRoleEnum.ADMIN);
    }
    
}
