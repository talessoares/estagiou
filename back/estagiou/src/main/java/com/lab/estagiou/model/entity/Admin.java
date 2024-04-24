package com.lab.estagiou.model.entity;

import com.lab.estagiou.controller.dto.request.auth.RequestCadastroAdmin;
import com.lab.estagiou.model.entity.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "admin")
@Table(name = "tb_admin")
@ToString
@NoArgsConstructor
public class Admin extends Usuario {

    public Admin(RequestCadastroAdmin request) {
        super(null, request.getEmail(), request.getSenha(), UserRole.ADMIN);
    }

}
