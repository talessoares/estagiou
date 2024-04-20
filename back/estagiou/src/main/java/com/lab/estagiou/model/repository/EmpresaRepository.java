package com.lab.estagiou.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.estagiou.model.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, UUID> {
    
    boolean existsByEmail(String email);
    boolean existsByCnpj(String cnpj);

}
