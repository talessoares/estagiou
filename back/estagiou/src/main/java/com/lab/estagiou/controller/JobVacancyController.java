package com.lab.estagiou.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab.estagiou.dto.request.model.jobvacancy.JobVacancyRegisterRequest;
import com.lab.estagiou.service.JobVacancyService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping(value = "/v1/jobvacancy", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Job Vacancy", description = "API for management of job vacancies")
public class JobVacancyController {

    @Autowired
    private JobVacancyService jobVacancyService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerJobVacancy(@RequestBody JobVacancyRegisterRequest request, Authentication authentication) {
        return jobVacancyService.registerJobVacancy(request, authentication);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listJobVacancies() {
        return jobVacancyService.listJobVacancies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> searchJobVacancyById(@PathVariable UUID id) {
        return jobVacancyService.searchJobVacancyById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteJobVacancyById(@PathVariable UUID id, Authentication authentication) {
        return jobVacancyService.deleteJobVacancyById(id, authentication);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateJobVacancy(@PathVariable UUID id, @RequestBody JobVacancyRegisterRequest request, Authentication authentication) {
        return jobVacancyService.updateJobVacancy(id, request, authentication);
    }
    
}
