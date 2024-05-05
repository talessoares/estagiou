package com.lab.estagiou.model.enrollment;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, UUID> {
    
}
