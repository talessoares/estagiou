package com.lab.estagiou.service.impl;

import org.springframework.security.core.Authentication;

import com.lab.estagiou.model.entity.Admin;

public abstract class ServiceUsuarioExistsAuthAdmin extends ServiceUsuarioExists {

    public boolean isAdmin(Authentication authentication) {
        return authentication.getPrincipal() instanceof Admin;
    }
    
}
