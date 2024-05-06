package com.lab.estagiou.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.lab.estagiou.dto.request.model.RequestRegisterCompany;
import com.lab.estagiou.exception.generic.EmailAlreadyRegisteredException;
import com.lab.estagiou.model.company.CompanyEntity;
import com.lab.estagiou.model.company.CompanyRepository;
import com.lab.estagiou.model.company.exception.CnpjAlreadyRegisteredException;
import com.lab.estagiou.model.company.exception.NoCompaniesRegisteredException;
import com.lab.estagiou.model.company.exception.NoCompanyFoundException;
import com.lab.estagiou.model.log.LogEnum;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.service.util.UtilService;

@Service
public class CompanyService extends UtilService {

    @Autowired
    private CompanyRepository companyRepository;

    private static final String COMPANY_NOT_FOUND = "Company not found: ";

    public ResponseEntity<Object> registerCompany(RequestRegisterCompany request) {
        validateUserAndCompany(request);
        
        UserEntity company = new CompanyEntity(request);
        userRepository.save(company);

        logger(LogEnum.INFO, "Registered company: " + company.getId());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<CompanyEntity>> listCompanies() {
        List<CompanyEntity> companies = companyRepository.findAll();

        if (companies.isEmpty()) {
            throw new NoCompaniesRegisteredException("No companies registered");
        }

        logger(LogEnum.INFO, "List companies: " + companies.size() + " companies");
        return ResponseEntity.ok(companies);
    }

    public ResponseEntity<CompanyEntity> searchCompanyById(UUID id) {
        CompanyEntity company = companyRepository.findById(id)
                .orElseThrow(() -> new NoCompanyFoundException(COMPANY_NOT_FOUND + id));

        logger(LogEnum.INFO, "Company found: " + company.getEmail());
        return ResponseEntity.ok(company);
    }

    public ResponseEntity<Object> deleteCompanyById(UUID id, Authentication authentication) {
        verifyAuthorization(authentication, id);

        if (!companyRepository.existsById(id)) {
            throw new NoCompanyFoundException(COMPANY_NOT_FOUND + id);
        }

        companyRepository.deleteById(id);

        logger(LogEnum.INFO, "Company deleted: " + id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> updateCompany(UUID id, RequestRegisterCompany request, Authentication authentication) {
        verifyAuthorization(authentication, id);

        CompanyEntity company = companyRepository.findById(id)
                .orElseThrow(() -> new NoCompanyFoundException(COMPANY_NOT_FOUND + id));

        company.update(request);
        companyRepository.save(company);

        logger(LogEnum.INFO, "Company updated: " + company.getId());
        return ResponseEntity.noContent().build();
    }

    private void validateUserAndCompany(RequestRegisterCompany request) {
        if (userExists(request)) {
            throw new EmailAlreadyRegisteredException("Email registration attempt: " + request.getEmail());
        }

        if (companyRepository.existsByCnpj(request.getCnpj())) {
            throw new CnpjAlreadyRegisteredException("CNPJ registration attempt: " + request.getCnpj());
        }
    }
}