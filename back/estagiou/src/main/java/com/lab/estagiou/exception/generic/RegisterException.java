package com.lab.estagiou.exception.generic;

// 400 - Bad Request
public class RegisterException extends IllegalArgumentException {

    public RegisterException(String message) {
        super(message);
    }
    
}
