package com.lab.estagiou.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.lab.estagiou.model.student.StudentEntity;

class StudentTests {

    private static final String EMAIL_STUDENT = "Nome do student não pode ser nulo";

    private static final String PASSWORD_STUDENT = "Email do student não pode ser nulo";

    @Test
    @DisplayName("Test create student")
    void testCreateStudent() {
        StudentEntity student = new StudentEntity("João", "Silva", EMAIL_STUDENT, PASSWORD_STUDENT);
        assertNotNull(student);
    }

    @Test
    @DisplayName("Test create student with null name")
    void testCreateStudentWithoutName() {
        try {
            new StudentEntity(null, "Silva", EMAIL_STUDENT, PASSWORD_STUDENT);
        } catch (IllegalArgumentException e) {
            assertEquals("Nome do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create student with empty name")
    void testCreateStudentWithEmptyName() {
        try {
            new StudentEntity("", "Silva", EMAIL_STUDENT, PASSWORD_STUDENT);
        } catch (IllegalArgumentException e) {
            assertEquals("Nome do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create student with null last name")
    void testCreateStudentWithoutLastName() {
        try {
            new StudentEntity("João", null, EMAIL_STUDENT, PASSWORD_STUDENT);
        } catch (IllegalArgumentException e) {
            assertEquals("Sobrenome do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create student with empty last name")
    void testCreateStudentWithEmptyLastName() {
        try {
            new StudentEntity("João", "", EMAIL_STUDENT, PASSWORD_STUDENT);
        } catch (IllegalArgumentException e) {
            assertEquals("Sobrenome do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create student with null email")
    void testCreateStudentWithoutEmail() {
        try {
            new StudentEntity("João", "Silva", null, PASSWORD_STUDENT);
        } catch (IllegalArgumentException e) {
            assertEquals("Email do aluno não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create student with empty email")
    void testCreateStudentWithEmptyEmail() {
        try {
            new StudentEntity("João", "Silva", "", PASSWORD_STUDENT);
        } catch (IllegalArgumentException e) {
            assertEquals("Email do aluno não pode ser nulo", e.getMessage());
        }
    }

    // @Test
    // @DisplayName("Test create student with null password")
    // void testCreateStudentWithoutCurso() {
    //     try {
    //         new Student("João", "Silva", EMAIL_STUDENT, PASSWORD_STUDENT);
    //     } catch (IllegalArgumentException e) {
    //         assertEquals("Curso do aluno não pode ser nulo", e.getMessage());
    //     }
    // }

    // @Test
    // @DisplayName("Test create student with empty password")
    // void testCreateStudentWithEndereco() {
    //     Student student = new Student("João", "Silva", EMAIL_STUDENT, PASSWORD_STUDENT);
    //     assertNotNull(student);
    // }

    @Test
    @DisplayName("Test add null enrollment")
    void testAddNullInscricao() {
        StudentEntity student = new StudentEntity("João", "Silva", EMAIL_STUDENT, PASSWORD_STUDENT);
        try {
            student.addEnrollment(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Inscrição não pode ser nula", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test remove null enrollment")
    void testRemoveNullInscricao() {
        StudentEntity student = new StudentEntity("João", "Silva", EMAIL_STUDENT, PASSWORD_STUDENT);
        try {
            student.removeEnrollment(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Inscrição não pode ser nula", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test contains null enrollment")
    void testContainsNullInscricao() {
        StudentEntity student = new StudentEntity("João", "Silva", EMAIL_STUDENT, PASSWORD_STUDENT);
        try {
            student.containsEnrollment(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Inscrição não pode ser nula", e.getMessage());
        }
    }

}
