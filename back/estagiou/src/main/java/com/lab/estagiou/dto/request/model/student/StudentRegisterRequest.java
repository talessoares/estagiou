package com.lab.estagiou.dto.request.model.student;

import com.lab.estagiou.dto.request.model.util.RequestEmailRegister;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StudentRegisterRequest extends RequestEmailRegister {

    private String lastName;
    
}

