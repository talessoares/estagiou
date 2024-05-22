package com.lab.estagiou.dto.request.model.company;

import com.lab.estagiou.dto.request.model.util.RequestEmailRegisterAddress;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CompanyRegisterRequest extends RequestEmailRegisterAddress {

    private String cnpj;
    private String accountableName;
    
}
