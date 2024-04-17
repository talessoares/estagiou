package com.lab.estagiou.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "curso")
@Table(name = "tb_curso")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    @OneToMany(mappedBy = "curso")
    private List<Aluno> alunos;

    private static final String ALUNO_NULO = "Aluno não pode ser nulo";

    public Curso(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do curso não pode ser nulo");
        }

        this.id = null;
        this.nome = nome;
        this.alunos = new ArrayList<>();
    }

    public boolean equalsName(String name) {
        return this.nome.equals(name);
    }

    public int getQuantidadeAlunos() {
        return this.alunos.size();
    }

    public boolean addAluno(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException(ALUNO_NULO);
        }

        return this.alunos.add(aluno);
    }

    public boolean removeAluno(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException(ALUNO_NULO);
        }

        return this.alunos.remove(aluno);
    }

    public boolean containsAluno(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException(ALUNO_NULO);
        }

        return this.alunos.contains(aluno);
    }

    public boolean isEmpty() {
        return this.alunos.isEmpty();
    }
    
}
