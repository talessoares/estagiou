package com.lab.estagiou.model.company;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lab.estagiou.dto.request.model.company.CompanyRegisterRequest;
import com.lab.estagiou.exception.generic.RegisterException;
import com.lab.estagiou.exception.generic.UpdateException;
import com.lab.estagiou.model.address.AddressEntity;
import com.lab.estagiou.model.jobvacancy.JobVacancyEntity;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.model.user.UserRoleEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "company")
@Table(name = "tb_company")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyEntity extends UserEntity {

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String cnpj;

    @Column(name = "accountable_name", nullable = false)
    private String accountableName;

    @OneToMany(mappedBy = "company")
    @JsonBackReference
    private List<JobVacancyEntity> jobVacancies = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "address_id", nullable = true)
    private AddressEntity address;

    private static final String JOB_VACANY_NULL = "Vaga não pode ser nula";
    
    public CompanyEntity(String name, String email, String password, String cnpj, String accountableName) {
        super(null, name, email, password, UserRoleEnum.COMPANY);

        if (cnpj == null || cnpj.isBlank()) {
            throw new RegisterException("CNPJ da empresa não pode ser nulo");
        }

        if (accountableName == null || accountableName.isBlank()) {
            throw new RegisterException("Responsável pela empresa não pode ser nulo");
        }

        this.cnpj = cnpj;
        this.accountableName = accountableName;
        this.jobVacancies = new ArrayList<>();
    }

    public CompanyEntity(CompanyRegisterRequest request) {
        this(request.getName(), request.getEmail(), request.getPassword(), request.getCnpj(), request.getAccountableName());
    }

    public boolean addJobVacancy(JobVacancyEntity jobVacancy) {
        if (jobVacancy == null) {
            throw new IllegalArgumentException(JOB_VACANY_NULL);
        }

        return this.jobVacancies.add(jobVacancy);
    }

    public boolean removeJobVacancy(JobVacancyEntity jobVacancy) {
        if (jobVacancy == null) {
            throw new IllegalArgumentException(JOB_VACANY_NULL);
        }

        return this.jobVacancies.remove(jobVacancy);
    }

    public boolean containsJobVacancy(JobVacancyEntity jobVacancy) {
        if (jobVacancy == null) {
            throw new IllegalArgumentException(JOB_VACANY_NULL);
        }

        return this.jobVacancies.contains(jobVacancy);
    }

    @JsonIgnore
    public boolean isJobVacanciesEmpty() {
        return this.jobVacancies.isEmpty();
    }

    @JsonIgnore
    public int getQuantityJobVacancies() {
        return this.jobVacancies.size();
    }

    public boolean equalsAddress(AddressEntity address) {
        return this.address.equals(address);
    }

    public void update(CompanyRegisterRequest request) {
        if (request == null) {
            throw new UpdateException("Requisição não pode ser nula");
        }

        if (request.getName() == null || request.getName().isBlank()) {
            throw new UpdateException("Nome não pode ser nulo");
        }
        this.name = request.getName();
    
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new UpdateException("Email não pode ser nulo");
        }
        super.setEmail(request.getEmail());
    
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new UpdateException("Senha não pode ser nula");
        }
        super.setPassword(request.getPassword());
    
        if (request.getCnpj() == null || request.getCnpj().isBlank()) {
            throw new UpdateException("CNPJ não pode ser nulo");
        }
        this.cnpj = request.getCnpj();
    
        if (request.getAccountableName() == null || request.getAccountableName().isBlank()) {
            throw new UpdateException("Responsável pela empresa não pode ser nulo");
        }
        this.accountableName = request.getAccountableName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        CompanyEntity company = (CompanyEntity) obj;

        return super.getId().equals(company.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}
