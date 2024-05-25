package com.lab.estagiou.model.company;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lab.estagiou.dto.request.model.company.CompanyRegisterRequest;
import com.lab.estagiou.dto.request.model.util.RequestAddress;
import com.lab.estagiou.exception.generic.RegisterException;
import com.lab.estagiou.exception.generic.UpdateException;
import com.lab.estagiou.model.address.AddressEntity;
import com.lab.estagiou.model.jobvacancy.JobVacancyEntity;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.model.user.UserRoleEnum;

import jakarta.persistence.CascadeType;
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

    @Column(unique = true, nullable = false)
    private String cnpj;

    @Column(name = "accountable_name", nullable = false)
    private String accountableName;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<JobVacancyEntity> jobVacancies = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", nullable = true)
    private AddressEntity address;

    private static final String JOB_VACANY_NULL = "Vaga não pode ser nula";
    
    public CompanyEntity(String name, String email, String password, String cnpj, String accountableName, RequestAddress address) {
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
        this.address = new AddressEntity(address);
    }

    public CompanyEntity(CompanyRegisterRequest request) {
        this(request.getName(), request.getEmail(), request.getPassword(), request.getCnpj(), request.getAccountableName(), request.getAddress());
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

        this.setName(validateAndAssign(this.getName(), request.getName(), "Nome da empresa não pode ser nulo"));
        this.setEmail(validateAndAssign(this.getEmail(), request.getEmail(), "Email da empresa não pode ser nulo"));
        this.cnpj = validateAndAssign(this.cnpj, request.getCnpj(), "CNPJ da empresa não pode ser nulo");
        this.accountableName = validateAndAssign(this.accountableName, request.getAccountableName(), "Responsável pela empresa não pode ser nulo");

        if (request.getPassword() == null) {
            this.setPassword(validateAndAssign(this.getPassword(), new BCryptPasswordEncoder().encode(request.getPassword()), "Senha da empresa não pode ser nula"));
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

        CompanyEntity company = (CompanyEntity) obj;

        return super.getId().equals(company.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}
