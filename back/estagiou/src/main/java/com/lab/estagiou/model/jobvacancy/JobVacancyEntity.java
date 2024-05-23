package com.lab.estagiou.model.jobvacancy;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lab.estagiou.dto.request.model.jobvacancy.JobVacancyRegisterRequest;
import com.lab.estagiou.exception.generic.RegisterException;
import com.lab.estagiou.exception.generic.UpdateException;
import com.lab.estagiou.model.admin.AdminEntity;
import com.lab.estagiou.model.company.CompanyEntity;
import com.lab.estagiou.model.enrollment.EnrollmentEntity;
import com.lab.estagiou.model.user.UserEntity;

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
    @JsonIgnore
    private Instant createdAt;

    @Column(name = "updated_at")
    @JsonIgnore
    private Instant updatedAt;

    @OneToMany(mappedBy = "jobVacancy")
    private List<EnrollmentEntity> enrollments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonManagedReference
    private CompanyEntity company;

    public JobVacancyEntity(JobVacancyRegisterRequest request, CompanyEntity company) {
        if (request == null) {
            throw new RegisterException("Request cannot be null");
        }

        if (company == null) {
            throw new RegisterException("Company cannot be null");
        }

        if (request.getTitle() == null || request.getTitle().isEmpty()) {
            throw new RegisterException("Título não pode ser vazio");
        }

        if (request.getRole() == null || request.getRole().isEmpty()) {
            throw new RegisterException("Cargo não pode ser vazio");
        }

        if (request.getDescription() == null || request.getDescription().isEmpty()) {
            throw new RegisterException("Descrição não pode ser vazia");
        }

        if (request.getSalary() == null || request.getSalary().isEmpty()) {
            throw new RegisterException("Salário não pode ser vazio");
        }

        if (request.getHours() == null || request.getHours().isEmpty()) {
            throw new RegisterException("Horas não podem ser vazias");
        }

        if (request.getModality() == null || request.getModality().isEmpty()) {
            throw new RegisterException("Modalidade não pode ser vazia");
        }

        this.title = request.getTitle();
        this.role = request.getRole();
        this.description = request.getDescription();
        this.salary = request.getSalary();
        this.hours = request.getHours();
        this.modality = request.getModality();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.company = company;
    }

    public void update(JobVacancyRegisterRequest request) {
        if (request == null) {
            throw new UpdateException("Request cannot be null");
        }

        if (request.getTitle() != null) {
            setTitle(request.getTitle());
        }

        if (request.getRole() != null) {
            setRole(request.getRole());
        }

        if (request.getDescription() != null) {
            setDescription(request.getDescription());
        }

        if (request.getSalary() != null) {
            setSalary(request.getSalary());
        }

        if (request.getHours() != null) {
            setHours(request.getHours());
        }

        if (request.getModality() != null) {
            setModality(request.getModality());
        }

        this.updatedAt = Instant.now();
    }

    public boolean equalsCompanyOrAdmin(Authentication authentication) {
        if (authentication == null) {
            return false;
        }

        UserEntity user = (UserEntity) authentication.getPrincipal();

        return equalsCompany(user) || user instanceof AdminEntity;
    }

    public boolean equalsCompany(UserEntity user) {
        if (user == null) {
            return false;
        }

        if (!(user instanceof CompanyEntity)) {
            return false;
        }

        return this.company.equals(user);
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new UpdateException("Título não pode ser vazio");
        }
        this.title = title;
    }

    public void setRole(String role) {
        if (role == null || role.isBlank()) {
            throw new UpdateException("Cargo não pode ser vazio");
        }
        this.role = role;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new UpdateException("Descrição não pode ser vazia");
        }
        this.description = description;
    }

    public void setSalary(String salary) {
        if (salary == null || salary.isBlank()) {
            throw new UpdateException("Salário não pode ser vazio");
        }
        this.salary = salary;
    }

    public void setHours(String hours) {
        if (hours == null || hours.isBlank()) {
            throw new UpdateException("Horas não podem ser vazias");
        }
        this.hours = hours;
    }

    public void setModality(String modality) {
        if (modality == null || modality.isBlank()) {
            throw new UpdateException("Modalidade não pode ser vazia");
        }
        this.modality = modality;
    }

}
