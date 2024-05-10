package com.lab.estagiou.dto.response.company.list;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.lab.estagiou.model.address.AddressEntity;
import com.lab.estagiou.model.company.CompanyEntity;
import com.lab.estagiou.model.jobvacancy.JobVacancyEntity;

import lombok.Data;

@Data
public class ResponseCompanyList {

    private UUID id;
    private String name;
    private String cnpj;
    private String accountableName;
    private List<ResponseJobVacancyList> jobVacancies;
    private AddressEntity address;

    public ResponseCompanyList(CompanyEntity companyEntity) {
        this.id = companyEntity.getId();
        this.name = companyEntity.getName();
        this.cnpj = companyEntity.getCnpj();
        this.accountableName = companyEntity.getAccountableName();
        this.address = companyEntity.getAddress();
        this.jobVacancies = new ArrayList<>();

        for (JobVacancyEntity jobVacancyEntity : companyEntity.getJobVacancies()) {
            this.jobVacancies.add(new ResponseJobVacancyList(jobVacancyEntity));
        }
    }
    
}
