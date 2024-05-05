package com.lab.estagiou.model.jobvacancy;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.lab.estagiou.model.company.CompanyEntity;
import com.lab.estagiou.model.enrollment.EnrollmentEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "jobVacancy")
@Table(name = "tb_job_vacancy")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobVacancyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String role;

    private String description;

    private String salary;

    private String hours;

    private String modality;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "jobVacancy")
    private List<EnrollmentEntity> enrollments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

}
