package com.lab.estagiou.model.company;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    
    boolean existsByEmail(String email);
    boolean existsByCnpj(String cnpj);

}
