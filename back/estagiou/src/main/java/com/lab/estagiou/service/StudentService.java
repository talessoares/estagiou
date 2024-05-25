package com.lab.estagiou.service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import com.lab.estagiou.dto.request.model.student.StudentRegisterRequest;
import com.lab.estagiou.exception.generic.EmailAlreadyRegisteredException;
import com.lab.estagiou.exception.generic.NoContentException;
import com.lab.estagiou.exception.generic.NotFoundException;
import com.lab.estagiou.model.emailconfirmationtoken.EmailConfirmationTokenEntity;
import com.lab.estagiou.model.emailconfirmationtoken.EmailConfirmationTokenRepository;
import com.lab.estagiou.model.log.LogEnum;
import com.lab.estagiou.model.student.StudentEntity;
import com.lab.estagiou.model.student.StudentRepository;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.service.util.UtilService;

@Service
public class StudentService extends UtilService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmailSendService emailService;

    @Autowired
    private EmailConfirmationTokenRepository emailConfirmationTokenRepository;

    @Value("${spring.mail.enable}")
    private boolean mailInviteEnabled;

    private static final String STUDENT_NOT_FOUND = "Student not found: ";

    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);

    public ResponseEntity<Object> registerStudent(StudentRegisterRequest request) {
        if (super.userExists(request)) {
            throw new EmailAlreadyRegisteredException("Email registration attempt: " + request.getEmail());
        }

        UserEntity student = new StudentEntity(request);

        if (!mailInviteEnabled) {
            student.setEnabled(true);
        }

        student = super.userRepository.save(student);
        log(LogEnum.INFO, "Student registered: " + student.getId(), HttpStatus.CREATED.value());

        if (mailInviteEnabled) {
            createConfirmationEmailAndSend(student);
        }

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<StudentEntity>> listStudents() {
        List<StudentEntity> students = studentRepository.findAll();

        if (students.isEmpty()) {
            throw new NoContentException("No students registered");
        }

        log(LogEnum.INFO, "List students: " + students.size() + " students", HttpStatus.OK.value());
        return ResponseEntity.ok(students);
    }

    public ResponseEntity<Object> searchStudentById(UUID id, Authentication authentication) {
        super.verifyAuthorization(authentication, id);

        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND + id));

        log(LogEnum.INFO, "Student found: " + student.getId(), HttpStatus.OK.value());
        return ResponseEntity.ok(student);
    }

    public ResponseEntity<Object> deleteStudentById(UUID id, Authentication authentication) {
        super.verifyAuthorization(authentication, id);

        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND + id));

        studentRepository.delete(student);

        log(LogEnum.INFO, "Student deleted: " + id, HttpStatus.NO_CONTENT.value());
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> updateStudent(UUID id, StudentRegisterRequest request, Authentication authentication) {
        super.verifyAuthorization(authentication, id);

        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND + id));

        student.update(request);
        studentRepository.save(student);

        log(LogEnum.INFO, "Student updated: " + student.getId(), HttpStatus.NO_CONTENT.value());
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Object> createConfirmationEmailAndSend(UserEntity user) {
        EmailConfirmationTokenEntity email = createConfirmationEmail(user);
        emailService.sendEmailAsync(email);
        return ResponseEntity.ok().build();
    }

    private EmailConfirmationTokenEntity createConfirmationEmail(UserEntity user) {
        String token = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), StandardCharsets.US_ASCII);
        EmailConfirmationTokenEntity emailConfirmationToken = new EmailConfirmationTokenEntity(token, user);

        return emailConfirmationTokenRepository.save(emailConfirmationToken);
    }
    
}
