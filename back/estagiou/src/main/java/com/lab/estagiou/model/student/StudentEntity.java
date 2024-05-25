package com.lab.estagiou.model.student;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lab.estagiou.dto.request.model.student.StudentRegisterRequest;
import com.lab.estagiou.exception.generic.RegisterException;
import com.lab.estagiou.exception.generic.UpdateException;
import com.lab.estagiou.model.address.AddressEntity;
import com.lab.estagiou.model.course.CourseEntity;
import com.lab.estagiou.model.enrollment.EnrollmentEntity;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.model.user.UserRoleEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "student")
@Table(name = "tb_student")
@Data
@ToString
@NoArgsConstructor
public class StudentEntity extends UserEntity {

    @Column(nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = true)
    private CourseEntity course;

    @OneToMany(mappedBy = "student")
    private List<EnrollmentEntity> enrollments;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", nullable = true)
    private AddressEntity address;

    private static final String ENROLLMENT_NULL = "Inscrição não pode ser nula";

    private static final String EXPECTED_DOMAIN = "@gmail.com";

    public StudentEntity(String name, String lastName, String email, String password) {
        super(null, name, email, password, UserRoleEnum.USER);

        if (name == null || name.isBlank()) {
            throw new RegisterException("Nome do aluno não pode ser nulo");
        }

        if (lastName == null || lastName.isBlank()) {
            throw new RegisterException("Sobrenome do aluno não pode ser nulo");
        }

        validStudentEmail(email);

        this.lastName = lastName;
        this.enrollments = new ArrayList<>();
    }

    public StudentEntity(StudentRegisterRequest request) {
        this(request.getName(), request.getLastName(), request.getEmail(), request.getPassword());
    }

    public void validStudentEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new RegisterException("Email do aluno não pode ser nulo");
        }

        if (!EmailValidator.getInstance().isValid(email)) {
            throw new RegisterException("Email inválido");
        }

        if (!email.contains(EXPECTED_DOMAIN)) {
            throw new RegisterException("Email inválido, o domínio esperado é " + EXPECTED_DOMAIN);
        }
    }

    public boolean addEnrollment(EnrollmentEntity enrollment) {
        if (enrollment == null) {
            throw new IllegalArgumentException(ENROLLMENT_NULL);
        }

        return this.enrollments.add(enrollment);
    }

    public boolean removeEnrollment(EnrollmentEntity enrollment) {
        if (enrollment == null) {
            throw new IllegalArgumentException(ENROLLMENT_NULL);
        }

        return this.enrollments.remove(enrollment);
    }

    public boolean containsEnrollment(EnrollmentEntity enrollment) {
        if (enrollment == null) {
            throw new IllegalArgumentException(ENROLLMENT_NULL);
        }

        return this.enrollments.contains(enrollment);
    }

    @JsonIgnore
    public boolean isEnrollmentsEmpty() {
        return this.enrollments.isEmpty();
    }

    @JsonIgnore
    public int getQuantityEnrollments() {
        return this.enrollments.size();
    }

    public boolean equalsCourse(CourseEntity course) {
        return this.course.equals(course);
    }

    public boolean equalsAddress(AddressEntity address) {
        return this.address.equals(address);
    }

    public void update(StudentRegisterRequest request) {
        if (request == null) {
            throw new UpdateException("Requisição de atualização do aluno não pode ser nula");
        }

        this.setName(validateAndAssign(super.getName(), request.getName(), "Nome do aluno não pode ser nulo"));
        this.setLastName(validateAndAssign(this.lastName, request.getLastName(), "Sobrenome do aluno não pode ser nulo"));
        this.setEmail(validateAndAssign(super.getEmail(), request.getEmail(), "Email do aluno não pode ser nulo"));

        if (request.getPassword() == null) {
            this.setPassword(validateAndAssign(this.getPassword(), new BCryptPasswordEncoder().encode(request.getPassword()), "Senha do aluno não pode ser nula"));
        }
    
        if (request.getAddress() != null) {
            if (this.address == null) {
                this.address = new AddressEntity(request.getAddress());
            } else {
                this.address.update(request.getAddress());
            }
        }
    }

    private String validateAndAssign(String originalValue, String value, String errorMessage) {
        if (value == null) {
            return originalValue;
        }

        if (value.isBlank()) {
            throw new UpdateException(errorMessage);
        }

        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        StudentEntity student = (StudentEntity) obj;

        return super.equals(student) && this.lastName.equals(student.getLastName());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


}
