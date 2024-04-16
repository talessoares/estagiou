package com.lab.estagiou.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.estagiou.model.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
    
}
