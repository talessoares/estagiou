package com.lab.estagiou.model.repository;

import java.util.UUID;

import com.lab.estagiou.model.entity.Aluno;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, UUID> {

    boolean existsByEmail(String email);
    
}
