package com.lab.estagiou.controller.dto.request.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RequestCadastroEmpresa extends RequestCadastro {

    private String cnpj;
    private String responsavel;
    
}
