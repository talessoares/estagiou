package com.lab.estagiou.service;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.lab.estagiou.dto.request.model.RequestRegisterStudent;
import com.lab.estagiou.dto.response.error.ErrorResponse;
import com.lab.estagiou.model.student.StudentEntity;
import com.lab.estagiou.model.student.StudentRepository;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.service.util.UtilUserAuthAndExists;

@Service
public class StudentService extends UtilUserAuthAndExists {

    @Autowired
    private StudentRepository studentRepository;

    private static final String NOT_AUTHORIZED = "Usuário não autorizado";

    public ResponseEntity<Object> registerStudent(RequestRegisterStudent request) {
        if (super.userExists(request)) {
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Email já cadastrado"));
        }

        try {
            UserEntity student = new StudentEntity(request);
            super.userRepository.save(student);

            URI location = URI.create("/v1/student/" + student.getId()); 

            return ResponseEntity.created(location).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    public ResponseEntity<List<StudentEntity>> listStudents() {
        List<StudentEntity> students = studentRepository.findAll();

        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(students);
    }

    public ResponseEntity<Object> searchStudentById(UUID id, Authentication authentication) {
        if (!super.userIsSameOrAdmin(authentication, id)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(HttpStatus.FORBIDDEN.value(), NOT_AUTHORIZED));
        }

        StudentEntity student = studentRepository.findById(id).orElse(null);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }

    public ResponseEntity<Object> deleteStudentById(UUID id, Authentication authentication) {
        if (!super.userIsSameOrAdmin(authentication, id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(HttpStatus.FORBIDDEN.value(), NOT_AUTHORIZED));
        }

        if (!studentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        studentRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> updateStudent(UUID id, RequestRegisterStudent request, Authentication authentication) {
        if (!super.userIsSameOrAdmin(authentication, id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(HttpStatus.FORBIDDEN.value(), NOT_AUTHORIZED));
        }

        StudentEntity student = studentRepository.findById(id).orElse(null);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        student.update(request);

        studentRepository.save(student);

        return ResponseEntity.noContent().build();
    }
    
}
