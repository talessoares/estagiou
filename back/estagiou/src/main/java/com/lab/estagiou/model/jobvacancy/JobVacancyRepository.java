package com.lab.estagiou.model.jobvacancy;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobVacancyRepository extends JpaRepository<JobVacancyEntity, UUID> {
    
}
