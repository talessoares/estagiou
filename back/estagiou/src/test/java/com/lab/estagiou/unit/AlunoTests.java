package com.lab.estagiou.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.lab.estagiou.model.entity.Aluno;
import com.lab.estagiou.model.entity.Curso;
import com.lab.estagiou.model.entity.Endereco;

class AlunoTests {

    private static final String EMAIL_ALUNO = "Nome do aluno não pode ser nulo";

    private static final Curso CURSO = new Curso("Engenharia de Software");

    private static final Endereco ENDERECO = new Endereco();

    @Test
    @DisplayName("Teste de criação de aluno")
    void testCreateAluno() {
        Aluno aluno = new Aluno("João", "Silva", EMAIL_ALUNO, CURSO, ENDERECO);
        assertNotNull(aluno);
    }

    @Test
    @DisplayName("Teste de criação de aluno sem nome")
    void testCreateAlunoWithoutName() {
        try {
            new Aluno(null, "Silva", EMAIL_ALUNO, CURSO, ENDERECO);
        } catch (IllegalArgumentException e) {
            assertEquals("Nome do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de aluno com nome vazio")
    void testCreateAlunoWithEmptyName() {
        try {
            new Aluno("", "Silva", EMAIL_ALUNO, CURSO, ENDERECO);
        } catch (IllegalArgumentException e) {
            assertEquals("Nome do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de aluno sem sobrenome")
    void testCreateAlunoWithoutLastName() {
        try {
            new Aluno("João", null, EMAIL_ALUNO, CURSO, ENDERECO);
        } catch (IllegalArgumentException e) {
            assertEquals("Sobrenome do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de aluno com sobrenome vazio")
    void testCreateAlunoWithEmptyLastName() {
        try {
            new Aluno("João", "", EMAIL_ALUNO, CURSO, ENDERECO);
        } catch (IllegalArgumentException e) {
            assertEquals("Sobrenome do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de aluno sem email")
    void testCreateAlunoWithoutEmail() {
        try {
            new Aluno("João", "Silva", null, CURSO, ENDERECO);
        } catch (IllegalArgumentException e) {
            assertEquals("Email do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de aluno com email vazio")
    void testCreateAlunoWithEmptyEmail() {
        try {
            new Aluno("João", "Silva", "", CURSO, ENDERECO);
        } catch (IllegalArgumentException e) {
            assertEquals("Email do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de aluno com curso nulo")
    void testCreateAlunoWithoutCurso() {
        try {
            new Aluno("João", "Silva", EMAIL_ALUNO, null, ENDERECO);
        } catch (IllegalArgumentException e) {
            assertEquals("Curso do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de aluno com endereço completo")
    void testCreateAlunoWithEndereco() {
        Aluno aluno = new Aluno("João", "Silva", EMAIL_ALUNO, CURSO, ENDERECO);
        assertNotNull(aluno);
    }

    @Test
    @DisplayName("Teste de adição de inscrição nula")
    void testAddNullInscricao() {
        Aluno aluno = new Aluno("João", "Silva", EMAIL_ALUNO, CURSO, ENDERECO);
        try {
            aluno.addInscricao(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Inscrição não pode ser nula", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de remoção de inscrição nula")
    void testRemoveNullInscricao() {
        Aluno aluno = new Aluno("João", "Silva", EMAIL_ALUNO, CURSO, ENDERECO);
        try {
            aluno.removeInscricao(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Inscrição não pode ser nula", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de verificação de inscrição nula")
    void testContainsNullInscricao() {
        Aluno aluno = new Aluno("João", "Silva", EMAIL_ALUNO, CURSO, ENDERECO);
        try {
            aluno.containsInscricao(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Inscrição não pode ser nula", e.getMessage());
        }
    }

}
