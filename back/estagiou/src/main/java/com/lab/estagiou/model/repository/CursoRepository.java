package com.lab.estagiou.model.repository;

import java.util.UUID;

import com.lab.estagiou.model.entity.Curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, UUID> {
    
}
