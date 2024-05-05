package com.lab.estagiou.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.lab.estagiou.model.course.CourseEntity;
import com.lab.estagiou.model.student.StudentEntity;

class CourseTests {

    @Test
    @DisplayName("Test create curso")
    void testCreateCourse() {
        CourseEntity course = new CourseEntity("Engenharia de Software");
        assertNotNull(course);
        assertTrue(course.equalsName("Engenharia de Software"));
    }

    @Test
    @DisplayName("Test create curso with null name")
    void testCreateCourseWithoutName() {
        try {
            new CourseEntity(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Nome do curso não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create curso with empty name")
    void testCreateCourseWithEmptyName() {
        try {
            new CourseEntity("");
        } catch (IllegalArgumentException e) {
            assertEquals("Nome do curso não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test equals name")
    void testEqualsName() {
        CourseEntity course = new CourseEntity("Engenharia de Software");
        assertTrue(course.equalsName("Engenharia de Software"));
    }

    @Test
    @DisplayName("Test equals name with different name, null and blank")
    void testEqualsNameWithDifferentNameNullAndBlank() {
        CourseEntity course = new CourseEntity("Engenharia de Software");
        assertTrue(!course.equalsName("Engenharia de Computação"));
        assertTrue(!course.equalsName(null));
        assertTrue(!course.equalsName(""));
    }

    @Test
    @DisplayName("Test add student")
    void testAddStudent() {
        CourseEntity course = new CourseEntity("Engenharia de Software");
        assertEquals(0, course.getQuantityStudents());

        StudentEntity student = new StudentEntity();
        course.addStudent(student);

        assertEquals(1, course.getQuantityStudents());
    }

    @Test
    @DisplayName("Test add aluno with null student")
    void testAddNullStudent() {
        CourseEntity course = new CourseEntity("Engenharia de Software");
        assertEquals(0, course.getQuantityStudents());

        try {
            course.addStudent(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Aluno não pode ser nulo", e.getMessage());
        }

        assertEquals(0, course.getQuantityStudents());
    }

    @Test
    @DisplayName("Test remove student")
    void testRemoveStudent() {
        CourseEntity course = new CourseEntity("Engenharia de Software");
        assertEquals(0, course.getQuantityStudents());

        StudentEntity student = new StudentEntity();
        course.addStudent(student);

        assertEquals(1, course.getQuantityStudents());

        course.removeStudent(student);

        assertEquals(0, course.getQuantityStudents());
    }

    @Test
    @DisplayName("Test remove student with null student")
    void testRemoveNullStudent() {
        CourseEntity course = new CourseEntity("Engenharia de Software");
        assertEquals(0, course.getQuantityStudents());

        try {
            course.removeStudent(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Aluno não pode ser nulo", e.getMessage());
        }

        assertEquals(0, course.getQuantityStudents());
    }

    @Test
    @DisplayName("Test contains student")
    void testContainsStudent() {
        CourseEntity course = new CourseEntity("Engenharia de Software");
        assertEquals(0, course.getQuantityStudents());

        StudentEntity student = new StudentEntity();
        course.addStudent(student);

        assertEquals(1, course.getQuantityStudents());
        assertTrue(course.containsStudent(student));
    }

    @Test
    @DisplayName("Test contains student with null student")
    void testContainsNullStudent() {
        CourseEntity course = new CourseEntity("Engenharia de Software");
        assertEquals(0, course.getQuantityStudents());

        try {
            course.containsStudent(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Aluno não pode ser nulo", e.getMessage());
        }

        assertEquals(0, course.getQuantityStudents());
    }

    @Test
    @DisplayName("Test is empty")
    void testIsEmpty() {
        CourseEntity course = new CourseEntity("Engenharia de Software");
        assertEquals(0, course.getQuantityStudents());
        assertTrue(course.isEmpty());

        StudentEntity student = new StudentEntity();
        course.addStudent(student);

        assertEquals(1, course.getQuantityStudents());
        assertTrue(!course.isEmpty());
    }

}
