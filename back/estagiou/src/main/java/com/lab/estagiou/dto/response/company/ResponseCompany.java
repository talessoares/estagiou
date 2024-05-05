package com.lab.estagiou.dto.response.company;

import java.util.List;

import com.lab.estagiou.model.address.AddressEntity;
import com.lab.estagiou.model.company.CompanyEntity;
import com.lab.estagiou.model.jobvacancy.JobVacancyEntity;

import lombok.Data;

@Data
public class ResponseCompany {

    private String name;
    private String email;
    private String password;
    private String cnpj;
    private String accountableName;
    private List<JobVacancyEntity> jobVacancies;
    private AddressEntity address;

    public ResponseCompany(CompanyEntity company) {
        this.name = company.getName();
        
        this.email = company.getEmail();
        this.password = company.getPassword();
        this.cnpj = company.getCnpj();
        this.accountableName = company.getAccountableName();
        this.jobVacancies = company.getJobVacancies();
        this.address = company.getAddress();
    }
    
}
