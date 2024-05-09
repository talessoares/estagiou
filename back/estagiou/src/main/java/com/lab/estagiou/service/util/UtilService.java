package com.lab.estagiou.service.util;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import com.lab.estagiou.dto.request.model.util.RequestEmail;
import com.lab.estagiou.exception.generic.UnauthorizedUserException;
import com.lab.estagiou.exception.handler.util.HandlerExceptionUtil;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.model.user.UserRepository;
import com.lab.estagiou.model.user.UserRoleEnum;

public abstract class UtilService extends HandlerExceptionUtil {

    @Autowired
    public UserRepository userRepository;

    public boolean userExists(RequestEmail request) {
        return userRepository.findByEmail(request.getEmail()) != null;
    }

    public boolean userExists(Authentication authentication) {
        return userRepository.findByEmail(((UserEntity) authentication.getPrincipal()).getEmail()) != null;
    }

    public void verifyAuthorization(Authentication authentication, UUID id) {
        if (!userIsSameOrAdmin(authentication, id)){
            throw new UnauthorizedUserException("Unauthorized access attempt: " + ((UserEntity) authentication.getPrincipal()).getId());
        }
    }

    private boolean userIsSameOrAdmin(Authentication authentication, UUID id) {
        if (authentication == null) {
            throw new UnauthorizedUserException("Unauthorized access attempt");
        }

        UserEntity user = (UserEntity) authentication.getPrincipal();
        return user.getId().equals(id) || userIsAdmin(authentication);
    }

    public boolean userIsAdmin(Authentication authentication) {
        return ((UserEntity) authentication.getPrincipal()).getRole().equals(UserRoleEnum.ADMIN);
    }

    
}
