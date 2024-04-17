package com.lab.estagiou.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "aluno")
@Table(name = "tb_aluno")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String sobrenome;

    private String email;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "aluno")
    private List<Inscricao> inscricoes;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    private static final String INSCRICAO_NULA = "Inscrição não pode ser nula";

    public Aluno(String nome, String sobrenome, String email, Curso curso, Endereco endereco) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do aluno não pode ser nulo");
        }

        if (sobrenome == null || sobrenome.isBlank()) {
            throw new IllegalArgumentException("Sobrenome do aluno não pode ser nulo");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email do aluno não pode ser nulo");
        }

        this.id = null;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.curso = curso;
        this.endereco = endereco;
        this.inscricoes = new ArrayList<>();
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

}
