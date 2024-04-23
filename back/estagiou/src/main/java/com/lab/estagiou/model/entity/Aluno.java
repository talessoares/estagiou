package com.lab.estagiou.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.lab.estagiou.controller.dto.request.auth.RequestCadastro;
import com.lab.estagiou.model.entity.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "aluno")
@Table(name = "tb_aluno")
@Data
@ToString
@NoArgsConstructor
public class Aluno extends Usuario {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = true)
    private Curso curso;

    @OneToMany(mappedBy = "aluno")
    private List<Inscricao> inscricoes;

    @OneToOne
    @JoinColumn(name = "endereco_id", nullable = true)
    private Endereco endereco;

    private static final String INSCRICAO_NULA = "Inscrição não pode ser nula";

    public Aluno(String nome, String sobrenome, String email, String senha) {
        super(null, email, senha, UserRole.USER);

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do aluno não pode ser nulo");
        }

        if (sobrenome == null || sobrenome.isBlank()) {
            throw new IllegalArgumentException("Sobrenome do aluno não pode ser nulo");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email do aluno não pode ser nulo");
        }

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.inscricoes = new ArrayList<>();
    }

    public Aluno(String nome, String sobrenome, String email, String senha, Endereco endereco) {
        this(nome, sobrenome, email, senha);
        this.endereco = endereco;
    }

    public Aluno(RequestCadastro requestCadastroAluno, String senha) {
        this(requestCadastroAluno.getNome(), requestCadastroAluno.getSobrenome(), requestCadastroAluno.getEmail(), senha);
    }

    public boolean addInscricao(Inscricao inscricao) {
        if (inscricao == null) {
            throw new IllegalArgumentException(INSCRICAO_NULA);
        }

        return this.inscricoes.add(inscricao);
    }

    public boolean removeInscricao(Inscricao inscricao) {
        if (inscricao == null) {
            throw new IllegalArgumentException(INSCRICAO_NULA);
        }

        return this.inscricoes.remove(inscricao);
    }

    public boolean containsInscricao(Inscricao inscricao) {
        if (inscricao == null) {
            throw new IllegalArgumentException(INSCRICAO_NULA);
        }

        return this.inscricoes.contains(inscricao);
    }

    public boolean isInscricoesEmpty() {
        return this.inscricoes.isEmpty();
    }

    public int getQuantidadeInscricoes() {
        return this.inscricoes.size();
    }

    public boolean equalsCurso(Curso curso) {
        return this.curso.equals(curso);
    }

    public boolean equalsEndereco(Endereco endereco) {
        return this.endereco.equals(endereco);
    }

    public void update(RequestCadastro requestCadastroAluno) {
        if (requestCadastroAluno.getNome() != null && !requestCadastroAluno.getNome().isBlank()) {
            this.nome = requestCadastroAluno.getNome();
        }

        if (requestCadastroAluno.getSobrenome() != null && !requestCadastroAluno.getSobrenome().isBlank()) {
            this.sobrenome = requestCadastroAluno.getSobrenome();
        }

        if (requestCadastroAluno.getEmail() != null && !requestCadastroAluno.getEmail().isBlank()) {
            super.setEmail(requestCadastroAluno.getEmail());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Aluno aluno = (Aluno) obj;

        return super.equals(aluno) && this.nome.equals(aluno.getNome()) && this.sobrenome.equals(aluno.getSobrenome());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
