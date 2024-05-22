package com.lab.estagiou.dto.request.model.util;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class RequestEmailRegisterAddress extends RequestEmailRegister {

    private RequestAddress address;
    
}
