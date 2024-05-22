package com.lab.estagiou.model.admin;

import com.lab.estagiou.dto.request.model.admin.AdminRegisterRequest;
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

}
