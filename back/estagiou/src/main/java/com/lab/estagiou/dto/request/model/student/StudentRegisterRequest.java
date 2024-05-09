package com.lab.estagiou.dto.request.model.student;

import com.lab.estagiou.dto.request.model.util.RequestRegister;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StudentRegisterRequest extends RequestRegister {

    private String lastName;
    
}

