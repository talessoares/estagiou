package com.lab.estagiou.dto.request.model;

import com.lab.estagiou.dto.request.model.util.RequestRegister;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RequestRegisterCompany extends RequestRegister {

    private String cnpj;
    private String accountableName;
    
}
