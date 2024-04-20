package com.lab.estagiou.controller.dto.response.aluno;

import java.util.List;

import com.lab.estagiou.model.entity.Aluno;
import com.lab.estagiou.model.entity.Curso;
import com.lab.estagiou.model.entity.Endereco;
import com.lab.estagiou.model.entity.Inscricao;

import lombok.Data;

@Data
public class ResponseAluno {

    private String nome;
    private String sobrenome;
    private String email;

    private Curso curso;
    private List<Inscricao> inscricoes;
    private Endereco endereco;

    public ResponseAluno(Aluno aluno) {
        this.nome = aluno.getNome();
        this.sobrenome = aluno.getSobrenome();
        this.email = aluno.getEmail();
        this.curso = aluno.getCurso();
        this.inscricoes = aluno.getInscricoes();
        this.endereco = aluno.getEndereco();
    }
    
}
