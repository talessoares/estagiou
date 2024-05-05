package com.lab.estagiou.model.enrollment;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import com.lab.estagiou.model.jobvacancy.JobVacancyEntity;
import com.lab.estagiou.model.student.StudentEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "enrollment")
@Table(name = "tb_enrollment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EnrollmentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private byte[] file;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "jobVacancy_id")
    private JobVacancyEntity jobVacancy;
    
}
