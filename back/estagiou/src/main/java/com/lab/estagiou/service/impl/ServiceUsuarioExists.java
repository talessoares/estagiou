package com.lab.estagiou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.lab.estagiou.controller.dto.request.auth.RequestCadastro;
import com.lab.estagiou.model.repository.UsuarioRepository;

public abstract class ServiceUsuarioExists {

    @Autowired
    public UsuarioRepository usuarioRepository;

    public boolean usuarioExists(RequestCadastro requestCadastro) {
        return usuarioRepository.findByEmail(requestCadastro.getEmail()) != null;
    }
    
}
