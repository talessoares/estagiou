package com.lab.estagiou.dto.request.model.util;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class RequestRegister extends RequestEmail {
    
    private String name;
    private String password;

}
