package com.lab.estagiou.controller.dto.request.aluno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestCadastroAluno {

    private String nome;
    private String sobrenome;
    private String email;

}
