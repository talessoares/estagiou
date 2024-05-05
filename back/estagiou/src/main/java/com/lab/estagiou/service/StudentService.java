package com.lab.estagiou.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lab.estagiou.dto.request.model.RequestRegisterStudent;
import com.lab.estagiou.dto.response.error.ErrorResponse;
import com.lab.estagiou.model.student.StudentEntity;
import com.lab.estagiou.model.student.StudentRepository;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.service.util.UtilUserExists;

@Service
public class StudentService extends UtilUserExists {

    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity<Object> registerCompany(RequestRegisterStudent request) {
        if (super.userExists(request)) {
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Email já cadastrado"));
        }

        try {
            UserEntity student = new StudentEntity(request);
            super.userRepository.save(student);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    public ResponseEntity<List<StudentEntity>> listStudents() {
        List<StudentEntity> students = studentRepository.findAll();

        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(students);
    }

    public ResponseEntity<StudentEntity> searchStudentById(UUID id) {
        StudentEntity student = studentRepository.findById(id).orElse(null);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }

    public ResponseEntity<Object> deleteStudentById(UUID id) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        studentRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> updateStudent(UUID id, RequestRegisterStudent request) {
        StudentEntity student = studentRepository.findById(id).orElse(null);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        student.update(request);

        studentRepository.save(student);

        return ResponseEntity.noContent().build();
    }
    
}
