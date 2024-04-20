package com.lab.estagiou.controller.dto.request.empresa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestCadastroEmpresa {

    private String nome;
    private String email;
    private String senha;
    private String cnpj;
    private String responsavel;

}
