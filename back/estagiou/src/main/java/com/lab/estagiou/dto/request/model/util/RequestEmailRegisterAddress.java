package com.lab.estagiou.dto.request.model.util;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class RequestEmailRegisterAddress extends RequestEmailRegister {

    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String number;
    private String complement;
    
}
