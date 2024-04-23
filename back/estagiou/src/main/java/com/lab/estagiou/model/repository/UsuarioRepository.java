package com.lab.estagiou.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.estagiou.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Usuario findByEmail(String email);
    
}
