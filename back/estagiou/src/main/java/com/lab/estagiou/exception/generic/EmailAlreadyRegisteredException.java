package com.lab.estagiou.exception.generic;

public class EmailAlreadyRegisteredException extends IllegalArgumentException {
    
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }

}
