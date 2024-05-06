package com.lab.estagiou.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.lab.estagiou.dto.request.model.RequestRegisterCompany;
import com.lab.estagiou.dto.response.error.ErrorResponse;
import com.lab.estagiou.model.company.CompanyEntity;
import com.lab.estagiou.model.company.CompanyRepository;
import com.lab.estagiou.model.log.LogEnum;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.service.util.UtilService;

@Service
public class CompanyService extends UtilService {

    @Autowired
    private CompanyRepository companyRepository;

    public ResponseEntity<Object> registerCompany(RequestRegisterCompany request) {

        if (super.userExists(request)) {
            logger(LogEnum.WARN, "Email registration attempt: " + request.getEmail());
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Email já cadastrado"));
        }

        if (companyRepository.existsByCnpj(request.getCnpj())) {
            logger(LogEnum.WARN, "CNPJ registration attempt: " + request.getCnpj());
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "CNPJ já cadastrado"));
        }

        UserEntity company = new CompanyEntity(request);
        super.userRepository.save(company);

        logger(LogEnum.INFO, "Registered company: " + company.getId());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<CompanyEntity>> listCompanies() {
        List<CompanyEntity> companies = companyRepository.findAll();

        if (companies.isEmpty()) {
            logger(LogEnum.INFO, "No companies registered");
            return ResponseEntity.noContent().build();
        }

        logger(LogEnum.INFO, "List companies: " + companies.size() + " companies");
        return ResponseEntity.ok(companies);
    }

    public ResponseEntity<CompanyEntity> searchCompanyById(UUID id) {
        CompanyEntity company = companyRepository.findById(id).orElse(null);

        if (company == null) {
            logger(LogEnum.WARN, "Company not found: " + id);
            return ResponseEntity.notFound().build();
        }

        logger(LogEnum.INFO, "Company found: " + company.getEmail());
        return ResponseEntity.ok(company);
    }

    public ResponseEntity<Object> deleteCompanyById(UUID id, Authentication authentication) {
        if (!super.userIsSameOrAdmin(authentication, id)) {
            logger(LogEnum.WARN, "Unauthorized user: " + ((UserEntity) authentication.getPrincipal()).getId());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Usuário não autorizado"));
        }

        if (!companyRepository.existsById(id)) {
            logger(LogEnum.WARN, "Company not found: " + id);
            return ResponseEntity.notFound().build();
        }

        companyRepository.deleteById(id);

        logger(LogEnum.INFO, "Company deleted: " + id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> updateCompany(UUID id, RequestRegisterCompany request, Authentication authentication) {
        if (!super.userIsSameOrAdmin(authentication, id)) {
            logger(LogEnum.WARN, "Unauthorized user: " + ((UserEntity) authentication.getPrincipal()).getId());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Usuário não autorizado"));
        }

        CompanyEntity company = companyRepository.findById(id).orElse(null);

        if (company == null) {
            logger(LogEnum.WARN, "Company not found: " + id);
            return ResponseEntity.notFound().build();
        }

        company.update(request);
        companyRepository.save(company);

        logger(LogEnum.INFO, "Company updated: " + company.getId());
        return ResponseEntity.noContent().build();
    }
    
}
