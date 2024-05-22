package com.lab.estagiou.model.student;

import java.util.ArrayList;
import java.util.List;

import com.lab.estagiou.dto.request.model.student.StudentRegisterRequest;
import com.lab.estagiou.model.address.AddressEntity;
import com.lab.estagiou.model.course.CourseEntity;
import com.lab.estagiou.model.enrollment.EnrollmentEntity;
import com.lab.estagiou.model.student.exception.RegisterStudentException;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.model.user.UserRoleEnum;

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

    @OneToOne
    @JoinColumn(name = "address_id", nullable = true)
    private AddressEntity address;

    private static final String ENROLLMENT_NULL = "Inscrição não pode ser nula";

    public StudentEntity(String name, String lastName, String email, String password) {
        super(null, name, email, password, UserRoleEnum.USER);

        if (name == null || name.isBlank()) {
            throw new RegisterStudentException("Nome do aluno não pode ser nulo");
        }

        if (lastName == null || lastName.isBlank()) {
            throw new RegisterStudentException("Sobrenome do aluno não pode ser nulo");
        }

        this.lastName = lastName;
        this.enrollments = new ArrayList<>();
    }

    public StudentEntity(String name, String lastName, String email, String password, AddressEntity address) {
        this(name, lastName, email, password);
        this.address = address;
    }

    public StudentEntity(StudentRegisterRequest request) {
        this(request.getName(), request.getLastName(), request.getEmail(), request.getPassword());
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

    public boolean isEnrollmentsEmpty() {
        return this.enrollments.isEmpty();
    }

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
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (request.getName() != null) {
            this.setName(request.getName());
        }

        if (request.getLastName() != null) {
            this.setLastName(request.getLastName());
        }

        if (request.getPassword() != null) {
            this.setPassword(request.getPassword());
        }
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

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new RegisterStudentException("Sobrenome do aluno não pode ser nulo");
        }
        this.lastName = lastName;
    }

    @Override
    public void setPassword(String password) {
        if (password == null || password.isBlank()) {
            throw new RegisterStudentException("Senha do aluno não pode ser nula");
        }
        super.setPassword(password);
    }


}
