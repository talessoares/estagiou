package com.lab.estagiou.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.lab.estagiou.dto.request.model.jobvacancy.JobVacancyRegisterRequest;
import com.lab.estagiou.exception.generic.UnauthorizedUserException;
import com.lab.estagiou.model.company.CompanyEntity;
import com.lab.estagiou.model.jobvacancy.JobVacancyEntity;
import com.lab.estagiou.model.jobvacancy.JobVacancyRepository;
import com.lab.estagiou.model.jobvacancy.exception.NoJobVacanciesRegisteredException;
import com.lab.estagiou.model.jobvacancy.exception.NoJobVacancyFoundException;
import com.lab.estagiou.model.log.LogEnum;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.service.util.UtilService;

@Service
public class JobVacancyService extends UtilService {

    @Autowired
    private JobVacancyRepository jobVacancyRepository;

    private static final String JOB_VACANCY_NOT_FOUND = "Job Vacancy not found: ";

    public ResponseEntity<Object> registerJobVacancy(JobVacancyRegisterRequest request, Authentication authentication) {
        if (authentication == null) {
            throw new UnauthorizedUserException("Unauthorized access attempt");
        }

        UserEntity user = (UserEntity) authentication.getPrincipal();

        if (!(user instanceof CompanyEntity)) {
            throw new UnauthorizedUserException("Unauthorized access attempt: " + user.getId());
        }

        CompanyEntity company = (CompanyEntity) user;
        JobVacancyEntity jobVacancy = new JobVacancyEntity(request, company);
        jobVacancyRepository.save(jobVacancy);

        logger(LogEnum.INFO, "Job vacancy registered: " + jobVacancy.getId(), HttpStatus.OK.value());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> listJobVacancies() {
        List<JobVacancyEntity> jobVacancies = jobVacancyRepository.findAll();

        if (jobVacancies.isEmpty()) {
            throw new NoJobVacanciesRegisteredException("No job vacancies registered");
        }

        logger(LogEnum.INFO, "List job vacancies: " + jobVacancies.size() + " job vacancies", HttpStatus.OK.value());
        return ResponseEntity.ok(jobVacancies);
    }

    public ResponseEntity<Object> searchJobVacancyById(UUID id) {
        JobVacancyEntity jobVacancy = jobVacancyRepository.findById(id)
                .orElseThrow(() -> new NoJobVacancyFoundException(JOB_VACANCY_NOT_FOUND + id));      

        logger(LogEnum.INFO, "Job Vacancy found: " + jobVacancy.getId(), HttpStatus.OK.value());
        return ResponseEntity.ok(jobVacancy);
    }

    public ResponseEntity<Object> deleteJobVacancyById(UUID id, Authentication authentication) {
        if (authentication == null) {
            throw new UnauthorizedUserException("Unauthorized access attempt");
        }

        JobVacancyEntity jobVacancy = jobVacancyRepository.findById(id)
                .orElseThrow(() -> new NoJobVacancyFoundException(JOB_VACANCY_NOT_FOUND + id));

        if (!jobVacancy.equalsCompanyOrAdmin(authentication)) {
            throw new UnauthorizedUserException("Unauthorized access attempt: " + ((UserEntity) authentication.getPrincipal()).getId());
        }

        jobVacancyRepository.deleteById(id);

        logger(LogEnum.INFO, "Job Vacancy deleted: " + id, HttpStatus.NO_CONTENT.value());
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> updateJobVacancy(UUID id, JobVacancyRegisterRequest request, Authentication authentication) {
        if (authentication == null) {
            throw new UnauthorizedUserException("Unauthorized access attempt");
        }

        JobVacancyEntity jobVacancy = jobVacancyRepository.findById(id)
                .orElseThrow(() -> new NoJobVacancyFoundException(JOB_VACANCY_NOT_FOUND + id));

        if (!jobVacancy.equalsCompanyOrAdmin(authentication)) {
            throw new UnauthorizedUserException("Unauthorized access attempt: " + ((UserEntity) authentication.getPrincipal()).getId());
        }

        jobVacancy.update(request);
        jobVacancyRepository.save(jobVacancy);

        logger(LogEnum.INFO, "Job Vacancy updated: " + jobVacancy.getId(), HttpStatus.NO_CONTENT.value());
        return ResponseEntity.noContent().build();
    }
    
}
