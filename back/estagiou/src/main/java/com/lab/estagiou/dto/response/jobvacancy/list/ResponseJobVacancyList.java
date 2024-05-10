package com.lab.estagiou.dto.response.jobvacancy.list;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.lab.estagiou.model.enrollment.EnrollmentEntity;
import com.lab.estagiou.model.jobvacancy.JobVacancyEntity;

import lombok.Data;

@Data
public class ResponseJobVacancyList {

    private UUID id;
    private String title;
    private String role;
    private String description;
    private String salary;
    private String hours;
    private String modality;
    private List<EnrollmentEntity> enrollments = new ArrayList<>();
    private ResponseCompanyList company;

    public ResponseJobVacancyList(JobVacancyEntity jobVacancyEntity) {
        this.id = jobVacancyEntity.getId();
        this.title = jobVacancyEntity.getTitle();
        this.role = jobVacancyEntity.getRole();
        this.description = jobVacancyEntity.getDescription();
        this.salary = jobVacancyEntity.getSalary();
        this.hours = jobVacancyEntity.getHours();
        this.modality = jobVacancyEntity.getModality();
        this.enrollments = jobVacancyEntity.getEnrollments();
        this.company = new ResponseCompanyList(jobVacancyEntity.getCompany());
    }
    
}
