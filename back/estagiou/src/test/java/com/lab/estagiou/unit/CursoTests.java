package com.lab.estagiou.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.lab.estagiou.model.entity.Aluno;
import com.lab.estagiou.model.entity.Curso;

class CursoTests {

    @Test
    @DisplayName("Teste de criação de curso")
    void testCreateCurso() {
        Curso curso = new Curso("Engenharia de Software");
        assertNotNull(curso);
        assertTrue(curso.equalsName("Engenharia de Software"));
    }

    @Test
    @DisplayName("Teste de criação de curso sem nome")
    void testCreateCursoWithoutName() {
        try {
            new Curso(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Nome do curso não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de curso com nome vazio")
    void testCreateCursoWithEmptyName() {
        try {
            new Curso("");
        } catch (IllegalArgumentException e) {
            assertEquals("Nome do curso não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de comparação de nome de curso")
    void testEqualsName() {
        Curso curso = new Curso("Engenharia de Software");
        assertTrue(curso.equalsName("Engenharia de Software"));
    }

    @Test
    @DisplayName("Teste de comparação de nome de curso com nome diferente, nulo e vazio")
    void testEqualsNameWithDifferentNameNullAndBlank() {
        Curso curso = new Curso("Engenharia de Software");
        assertTrue(!curso.equalsName("Engenharia de Computação"));
        assertTrue(!curso.equalsName(null));
        assertTrue(!curso.equalsName(""));
    }

    @Test
    @DisplayName("Teste de adicionar aluno em curso")
    void testAddAluno() {
        Curso curso = new Curso("Engenharia de Software");
        assertEquals(0, curso.getQuantidadeAlunos());

        Aluno aluno = new Aluno();
        curso.addAluno(aluno);

        assertEquals(1, curso.getQuantidadeAlunos());
    }

    @Test
    @DisplayName("Teste de adicionar aluno nulo em curso")
    void testAddNullAluno() {
        Curso curso = new Curso("Engenharia de Software");
        assertEquals(0, curso.getQuantidadeAlunos());

        try {
            curso.addAluno(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Aluno não pode ser nulo", e.getMessage());
        }

        assertEquals(0, curso.getQuantidadeAlunos());
    }

    @Test
    @DisplayName("Teste de remover aluno de curso")
    void testRemoveAluno() {
        Curso curso = new Curso("Engenharia de Software");
        assertEquals(0, curso.getQuantidadeAlunos());

        Aluno aluno = new Aluno();
        curso.addAluno(aluno);

        assertEquals(1, curso.getQuantidadeAlunos());

        curso.removeAluno(aluno);

        assertEquals(0, curso.getQuantidadeAlunos());
    }

    @Test
    @DisplayName("Teste de remover aluno nulo de curso")
    void testRemoveNullAluno() {
        Curso curso = new Curso("Engenharia de Software");
        assertEquals(0, curso.getQuantidadeAlunos());

        try {
            curso.removeAluno(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Aluno não pode ser nulo", e.getMessage());
        }

        assertEquals(0, curso.getQuantidadeAlunos());
    }

    @Test
    @DisplayName("Teste de verificar se curso contém aluno")
    void testContainsAluno() {
        Curso curso = new Curso("Engenharia de Software");
        assertEquals(0, curso.getQuantidadeAlunos());

        Aluno aluno = new Aluno();
        curso.addAluno(aluno);

        assertEquals(1, curso.getQuantidadeAlunos());
        assertTrue(curso.containsAluno(aluno));
    }

    @Test
    @DisplayName("Teste de verificar se curso contém aluno nulo")
    void testContainsNullAluno() {
        Curso curso = new Curso("Engenharia de Software");
        assertEquals(0, curso.getQuantidadeAlunos());

        try {
            curso.containsAluno(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Aluno não pode ser nulo", e.getMessage());
        }

        assertEquals(0, curso.getQuantidadeAlunos());
    }

    @Test
    @DisplayName("Teste de verificar se curso está vazio")
    void testIsEmpty() {
        Curso curso = new Curso("Engenharia de Software");
        assertEquals(0, curso.getQuantidadeAlunos());
        assertTrue(curso.isEmpty());

        Aluno aluno = new Aluno();
        curso.addAluno(aluno);

        assertEquals(1, curso.getQuantidadeAlunos());
        assertTrue(!curso.isEmpty());
    }

}
