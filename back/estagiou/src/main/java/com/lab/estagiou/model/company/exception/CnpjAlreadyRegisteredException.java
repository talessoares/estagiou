package com.lab.estagiou.model.company.exception;

public class CnpjAlreadyRegisteredException extends IllegalArgumentException {

    public CnpjAlreadyRegisteredException(String message) {
        super(message);
    }
    
}
