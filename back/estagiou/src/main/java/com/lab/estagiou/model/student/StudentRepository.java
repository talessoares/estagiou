package com.lab.estagiou.model.student;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {

    boolean existsByEmail(String email);
    
}
