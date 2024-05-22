package com.lab.estagiou.exception.generic;

public class EmailAlreadyRegisteredException extends RuntimeException {
    
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }

}
