package com.lab.estagiou.dto.request.model.company;

import com.lab.estagiou.dto.request.model.util.RequestEmailAddressRegister;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CompanyRegisterRequest extends RequestEmailAddressRegister {

    private String cnpj;
    private String accountableName;
    
}
