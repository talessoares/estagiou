package com.lab.estagiou.model.admin;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lab.estagiou.dto.request.model.admin.AdminRegisterRequest;
import com.lab.estagiou.exception.generic.UpdateException;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.model.user.UserRoleEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "admin")
@Table(name = "tb_admin")
@ToString
@NoArgsConstructor
public class AdminEntity extends UserEntity {

    public AdminEntity(AdminRegisterRequest request) {
        super(null, request.getName(), request.getEmail(), request.getPassword(), UserRoleEnum.ADMIN);
    }

    public AdminEntity(AdminRegisterRequest request, boolean active) {
        super(null, request.getName(), request.getEmail(), request.getPassword(), UserRoleEnum.ADMIN);
        super.setEnabled(active);
    }

    public void update(AdminRegisterRequest request) {
        if (request == null) {
            throw new UpdateException("Requisição de atualização do admin não pode ser nula");
        }

        this.setName(validateAndAssign(this.getName(), request.getName(), "Nome do admin não pode ser nulo"));
        this.setEmail(validateAndAssign(this.getEmail(), request.getEmail(), "Email do admin não pode ser nulo"));

        if (request.getPassword() == null) {
            this.setPassword(validateAndAssign(this.getPassword(), new BCryptPasswordEncoder().encode(request.getPassword()), "Senha do admin não pode ser nula"));
        }
    }

    private String validateAndAssign(String originalValue, String value, String errorMessage) {
        if (value == null) {
            return originalValue;
        }

        if (value.isBlank()) {
            throw new UpdateException(errorMessage);
        }

        return value;
    }

}
