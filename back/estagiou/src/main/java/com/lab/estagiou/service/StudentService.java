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
import com.lab.estagiou.model.log.LogEnum;
import com.lab.estagiou.model.student.StudentEntity;
import com.lab.estagiou.model.student.StudentRepository;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.service.util.UtilService;

@Service
public class StudentService extends UtilService {

    @Autowired
    private StudentRepository studentRepository;

    private static final String NOT_AUTHORIZED = "Usuário não autorizado";

    public ResponseEntity<Object> registerStudent(RequestRegisterStudent request) {
        if (super.userExists(request)) {
            logger(LogEnum.WARN, "Email registration attempt: " + request.getEmail());
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Email já cadastrado"));
        }

        UserEntity student = new StudentEntity(request);
        super.userRepository.save(student);

        URI location = URI.create("/v1/student/" + student.getId()); 

        logger(LogEnum.INFO, "Student registered: " + student.getId());
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<List<StudentEntity>> listStudents() {
        List<StudentEntity> students = studentRepository.findAll();

        if (students.isEmpty()) {
            logger(LogEnum.INFO, "No students registered");
            return ResponseEntity.noContent().build();
        }

        logger(LogEnum.INFO, "List students: " + students.size() + " students");
        return ResponseEntity.ok(students);
    }

    public ResponseEntity<Object> searchStudentById(UUID id, Authentication authentication) {
        if (!super.userIsSameOrAdmin(authentication, id)){
            logger(LogEnum.WARN, "Unauthorized access attempt: " + ((UserEntity) authentication.getPrincipal()).getId());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(HttpStatus.FORBIDDEN.value(), NOT_AUTHORIZED));
        }

        StudentEntity student = studentRepository.findById(id).orElse(null);

        if (student == null) {
            logger(LogEnum.WARN, "Student not found: " + id);
            return ResponseEntity.notFound().build();
        }

        logger(LogEnum.INFO, "Student found: " + student.getId());
        return ResponseEntity.ok(student);
    }

    public ResponseEntity<Object> deleteStudentById(UUID id, Authentication authentication) {
        if (!super.userIsSameOrAdmin(authentication, id)) {
            logger(LogEnum.WARN, "Unauthorized access attempt: " + ((UserEntity) authentication.getPrincipal()).getId());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(HttpStatus.FORBIDDEN.value(), NOT_AUTHORIZED));
        }

        if (!studentRepository.existsById(id)) {
            logger(LogEnum.WARN, "Student not found: " + id);
            return ResponseEntity.notFound().build();
        }

        studentRepository.deleteById(id);

        logger(LogEnum.INFO, "Student deleted: " + id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> updateStudent(UUID id, RequestRegisterStudent request, Authentication authentication) {
        if (!super.userIsSameOrAdmin(authentication, id)) {
            logger(LogEnum.WARN, "Unauthorized access attempt: " + ((UserEntity) authentication.getPrincipal()).getId());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(HttpStatus.FORBIDDEN.value(), NOT_AUTHORIZED));
        }

        StudentEntity student = studentRepository.findById(id).orElse(null);

        if (student == null) {
            logger(LogEnum.WARN, "Student not found: " + id);
            return ResponseEntity.notFound().build();
        }

        student.update(request);
        studentRepository.save(student);

        logger(LogEnum.INFO, "Student updated: " + student.getId());
        return ResponseEntity.noContent().build();
    }
    
}
