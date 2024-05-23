package com.lab.estagiou.dto.request.model.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RequestEmailAddressRegister extends RequestEmailRegister {

    private RequestAddress address;
    
}
