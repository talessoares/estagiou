package com.lab.estagiou.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.lab.estagiou.dto.request.model.company.CompanyRegisterRequest;
import com.lab.estagiou.exception.generic.EmailAlreadyRegisteredException;
import com.lab.estagiou.exception.generic.NoContentException;
import com.lab.estagiou.exception.generic.NotFoundException;
import com.lab.estagiou.model.company.CompanyEntity;
import com.lab.estagiou.model.company.CompanyRepository;
import com.lab.estagiou.model.company.exception.CnpjAlreadyRegisteredException;
import com.lab.estagiou.model.log.LogEnum;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.service.util.UtilService;

@Service
public class CompanyService extends UtilService {

    @Autowired
    private CompanyRepository companyRepository;

    private static final String COMPANY_NOT_FOUND = "Company not found: ";

    public ResponseEntity<Object> registerCompany(CompanyRegisterRequest request) {
        validateUserAndCompany(request);
        
        UserEntity company = new CompanyEntity(request);
        userRepository.save(company);

        log(LogEnum.INFO, "Registered company: " + company.getId(), HttpStatus.OK.value());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<CompanyEntity>> listCompanies() {
        List<CompanyEntity> companies = companyRepository.findAll();

        if (companies.isEmpty()) {
            throw new NoContentException("No companies registered");
        }

        log(LogEnum.INFO, "List companies: " + companies.size() + " companies", HttpStatus.OK.value());
        return ResponseEntity.ok(companies);
    }

    public ResponseEntity<CompanyEntity> searchCompanyById(UUID id) {
        CompanyEntity company = companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(COMPANY_NOT_FOUND + id));

        log(LogEnum.INFO, "Company found: " + company.getEmail(), HttpStatus.OK.value());
        return ResponseEntity.ok(company);
    }

    public ResponseEntity<Object> deleteCompanyById(UUID id, Authentication authentication) {
        verifyAuthorization(authentication, id);

        if (!companyRepository.existsById(id)) {
            throw new NotFoundException(COMPANY_NOT_FOUND + id);
        }

        companyRepository.deleteById(id);

        log(LogEnum.INFO, "Company deleted: " + id, HttpStatus.NO_CONTENT.value());
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> updateCompany(UUID id, CompanyRegisterRequest request, Authentication authentication) {
        verifyAuthorization(authentication, id);

        CompanyEntity company = companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(COMPANY_NOT_FOUND + id));

        company.update(request);
        companyRepository.save(company);

        log(LogEnum.INFO, "Company updated: " + company.getId(), HttpStatus.NO_CONTENT.value());
        return ResponseEntity.noContent().build();
    }

    private void validateUserAndCompany(CompanyRegisterRequest request) {
        if (userExists(request)) {
            throw new EmailAlreadyRegisteredException("Email já cadastrado: " + request.getEmail());
        }

        if (companyRepository.existsByCnpj(request.getCnpj())) {
            throw new CnpjAlreadyRegisteredException("CNPJ já cadastrado: " + request.getCnpj());
        }
    }
}