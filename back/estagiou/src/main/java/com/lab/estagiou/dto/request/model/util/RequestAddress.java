package com.lab.estagiou.dto.request.model.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAddress {

    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String number;
    private String complement;
    
}
