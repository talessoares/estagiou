package com.lab.estagiou.dto.request.model.util;

import lombok.Data;

@Data
public abstract class RequestRegister {
    
    private String name;
    private String email;
    private String password;

}
