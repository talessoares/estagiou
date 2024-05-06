package com.lab.estagiou.service.util;

import java.util.UUID;

import org.springframework.security.core.Authentication;

import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.model.user.UserRoleEnum;

public abstract class UtilUserAuthAndExists extends UtilUserExists {

    public boolean userIsSameOrAdmin(Authentication authentication, UUID id) {
        if (authentication == null) {
            return false;
        }

        UserEntity user = (UserEntity) authentication.getPrincipal();
        return user.getId().equals(id) || user.getRole().equals(UserRoleEnum.ADMIN);
    }
    
}
