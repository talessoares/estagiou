package com.lab.estagiou.controller.dto.request.auth;

import lombok.Data;

@Data
public abstract class RequestCadastro {
    
    private String nome;
    private String email;
    private String senha;

}
