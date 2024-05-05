package com.lab.estagiou.dto.response.student;

import java.util.List;

import com.lab.estagiou.model.address.AddressEntity;
import com.lab.estagiou.model.course.CourseEntity;
import com.lab.estagiou.model.enrollment.EnrollmentEntity;
import com.lab.estagiou.model.student.StudentEntity;

import lombok.Data;

@Data
public class ResponseStudent {

    private String name;
    private String lastName;
    private String email;

    private CourseEntity course;
    private List<EnrollmentEntity> enrollments;
    private AddressEntity address;

    public ResponseStudent(StudentEntity student) {
        this.name = student.getName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
        this.course = student.getCourse();
        this.enrollments = student.getEnrollments();
        this.address = student.getAddress();
    }
    
}
