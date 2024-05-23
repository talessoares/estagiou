package com.lab.estagiou.dto.request.model.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public abstract class RequestEmailRegister extends RequestEmail {
    
    private String name;
    private String password;

}
