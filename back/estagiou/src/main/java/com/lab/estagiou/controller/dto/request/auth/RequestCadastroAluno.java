package com.lab.estagiou.controller.dto.request.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RequestCadastroAluno extends RequestCadastro {

    private String sobrenome;
    
}
