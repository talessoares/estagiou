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
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.service.util.UtilUserAuthAndExists;

@Service
public class CompanyService extends UtilUserAuthAndExists {

    @Autowired
    private CompanyRepository companyRepository;

    public ResponseEntity<Object> registerCompany(RequestRegisterCompany request) {
        if (super.userExists(request)) {
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Email já cadastrado"));
        }

        if (companyRepository.existsByCnpj(request.getCnpj())) {
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "CNPJ já cadastrado"));
        }

        try {
            UserEntity company = new CompanyEntity(request);
            super.userRepository.save(company);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    public ResponseEntity<List<CompanyEntity>> listCompanies() {
        List<CompanyEntity> companies = companyRepository.findAll();

        if (companies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(companies);
    }

    public ResponseEntity<CompanyEntity> searchCompanyById(UUID id) {
        CompanyEntity company = companyRepository.findById(id).orElse(null);

        if (company == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(company);
    }

    public ResponseEntity<Object> deleteCompanyById(UUID id, Authentication authentication) {
        if (!super.userIsSameOrAdmin(authentication, id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Usuário não autorizado"));
        }

        if (!companyRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        companyRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> updateCompany(UUID id, RequestRegisterCompany request, Authentication authentication) {
        if (!super.userIsSameOrAdmin(authentication, id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Usuário não autorizado"));
        }

        CompanyEntity company = companyRepository.findById(id).orElse(null);

        if (company == null) {
            return ResponseEntity.notFound().build();
        }

        company.update(request);

        companyRepository.save(company);

        return ResponseEntity.noContent().build();
    }
    
}
