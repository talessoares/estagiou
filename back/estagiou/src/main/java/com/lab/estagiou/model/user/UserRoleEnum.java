package com.lab.estagiou.model.user;

public enum UserRoleEnum {

    ADMIN("ADMIN"),
    USER("USER"),
    COMPANY("COMPANY");

    private String role;

    UserRoleEnum (String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
    
}
