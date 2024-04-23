package com.lab.estagiou.controller.dto.request.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestCadastro {

    // COMUM
    private String nome;
    private String email;
    private String senha;
    private String role;

    // ALUNO
    private String sobrenome;
    
    // EMPRESA
    private String cnpj;
    private String responsavel;

    public void validar() {
        if (role == null || role.isEmpty()) {
            throw new RuntimeException("ROLE IS EMPTY");
        }

        if (role.equals("ALUNO")) {
            validarAluno();
        } else if (role.equals("EMPRESA")) {
            validarEmpresa();
        } else if(role.equals("ADMIN")) {
            validarAdmin();
        } else {
            throw new RuntimeException("ROLE NOT FOUND");
        }
    }

    private void validarAluno() {
        if (nome == null || nome.isEmpty()) {
            throw new RuntimeException("NAME IS EMPTY");
        }

        if (sobrenome == null || sobrenome.isEmpty()) {
            throw new RuntimeException("LAST NAME IS EMPTY");
        }
    }

    private void validarEmpresa() {
        if (nome == null || nome.isEmpty()) {
            throw new RuntimeException("NAME IS EMPTY");
        }

        if (cnpj == null || cnpj.isEmpty()) {
            throw new RuntimeException("CNPJ IS EMPTY");
        }

        if (responsavel == null || responsavel.isEmpty()) {
            throw new RuntimeException("RESPONSIBLE IS EMPTY");
        }
    }

    private void validarAdmin() {
        if (nome == null || nome.isEmpty()) {
            throw new RuntimeException("NAME IS EMPTY");
        }
    }

}
