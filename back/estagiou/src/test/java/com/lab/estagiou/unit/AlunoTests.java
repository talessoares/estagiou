package com.lab.estagiou.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.lab.estagiou.model.entity.Aluno;

class AlunoTests {

    private static final String EMAIL_ALUNO = "Nome do aluno não pode ser nulo";

    @Test
    @DisplayName("Teste de criação de aluno")
    void testCreateAluno() {
        Aluno aluno = new Aluno("João", "Silva", EMAIL_ALUNO, null);
        assertNotNull(aluno);
    }

    @Test
    @DisplayName("Teste de criação de aluno sem nome")
    void testCreateAlunoWithoutName() {
        try {
            new Aluno(null, "Silva", EMAIL_ALUNO, null);
        } catch (IllegalArgumentException e) {
            assertEquals("Nome do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de aluno com nome vazio")
    void testCreateAlunoWithEmptyName() {
        try {
            new Aluno("", "Silva", EMAIL_ALUNO, null);
        } catch (IllegalArgumentException e) {
            assertEquals("Nome do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de aluno sem sobrenome")
    void testCreateAlunoWithoutLastName() {
        try {
            new Aluno("João", null, EMAIL_ALUNO, null);
        } catch (IllegalArgumentException e) {
            assertEquals("Sobrenome do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de aluno com sobrenome vazio")
    void testCreateAlunoWithEmptyLastName() {
        try {
            new Aluno("João", "", EMAIL_ALUNO, null);
        } catch (IllegalArgumentException e) {
            assertEquals("Sobrenome do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de aluno sem email")
    void testCreateAlunoWithoutEmail() {
        try {
            new Aluno("João", "Silva", null, null);
        } catch (IllegalArgumentException e) {
            assertEquals("Email do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de aluno com email vazio")
    void testCreateAlunoWithEmptyEmail() {
        try {
            new Aluno("João", "Silva", "", null);
        } catch (IllegalArgumentException e) {
            assertEquals("Email do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de aluno com curso nulo")
    void testCreateAlunoWithoutCurso() {
        try {
            new Aluno("João", "Silva", EMAIL_ALUNO, null);
        } catch (IllegalArgumentException e) {
            assertEquals("Curso do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de aluno com endereço completo")
    void testCreateAlunoWithEndereco() {
        Aluno aluno = new Aluno("João", "Silva", EMAIL_ALUNO, null);
        assertNotNull(aluno);
    }

    @Test
    @DisplayName("Teste de adição de inscrição nula")
    void testAddNullInscricao() {
        Aluno aluno = new Aluno("João", "Silva", EMAIL_ALUNO, null);
        try {
            aluno.addInscricao(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Inscrição não pode ser nula", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de remoção de inscrição nula")
    void testRemoveNullInscricao() {
        Aluno aluno = new Aluno("João", "Silva", EMAIL_ALUNO, null);
        try {
            aluno.removeInscricao(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Inscrição não pode ser nula", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de verificação de inscrição nula")
    void testContainsNullInscricao() {
        Aluno aluno = new Aluno("João", "Silva", EMAIL_ALUNO, null);
        try {
            aluno.containsInscricao(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Inscrição não pode ser nula", e.getMessage());
        }
    }

}
