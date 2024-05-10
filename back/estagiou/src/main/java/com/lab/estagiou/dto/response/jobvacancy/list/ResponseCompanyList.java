package com.lab.estagiou.dto.response.jobvacancy.list;

import java.util.UUID;

import com.lab.estagiou.model.address.AddressEntity;
import com.lab.estagiou.model.company.CompanyEntity;

import lombok.Data;

@Data
public class ResponseCompanyList {

    private UUID id;
    private String name;
    private String cnpj;
    private String accountableName;
    private AddressEntity address;

    public ResponseCompanyList(CompanyEntity companyEntity) {
        this.id = companyEntity.getId();
        this.name = companyEntity.getName();
        this.cnpj = companyEntity.getCnpj();
        this.accountableName = companyEntity.getAccountableName();
        this.address = companyEntity.getAddress();
    }
    
}
