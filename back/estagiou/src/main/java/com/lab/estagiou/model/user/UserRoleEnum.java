package com.lab.estagiou.model.user;

public enum UserRoleEnum {

    ADMIN("admin"),
    USER("user"),
    COMPANY("company");

    private String role;

    UserRoleEnum (String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
    
}
