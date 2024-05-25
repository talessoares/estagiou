package com.lab.estagiou.service;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.lab.estagiou.dto.request.model.jobvacancy.JobVacancyRegisterRequest;
import com.lab.estagiou.exception.generic.NoContentException;
import com.lab.estagiou.exception.generic.NotFoundException;
import com.lab.estagiou.exception.generic.UnauthorizedUserException;
import com.lab.estagiou.model.company.CompanyEntity;
import com.lab.estagiou.model.jobvacancy.JobVacancyEntity;
import com.lab.estagiou.model.jobvacancy.JobVacancyRepository;
import com.lab.estagiou.model.log.LogEnum;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.service.util.UtilService;

@Service
public class JobVacancyService extends UtilService {

    @Autowired
    private JobVacancyRepository jobVacancyRepository;

    private static final String JOB_VACANCY_NOT_FOUND = "Job Vacancy not found: ";

    public ResponseEntity<JobVacancyEntity> registerJobVacancy(JobVacancyRegisterRequest request, Authentication authentication) {
        if (authentication == null) {
            throw new UnauthorizedUserException(UNAUTHORIZED_ACESS_ATTEMPT);
        }

        UserEntity user = (UserEntity) authentication.getPrincipal();

        if (!(user instanceof CompanyEntity)) {
            throw new UnauthorizedUserException(UNAUTHORIZED_ACESS_ATTEMPT_DOTS + user.getId());
        }

        CompanyEntity company = (CompanyEntity) user;
        JobVacancyEntity jobVacancy = new JobVacancyEntity(request, company);
        jobVacancyRepository.save(jobVacancy);

        URI location = URI.create("/jobvacancy/" + jobVacancy.getId());

        log(LogEnum.INFO, "Job vacancy registered: " + jobVacancy.getId(), HttpStatus.OK.value());
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<List<JobVacancyEntity>> listJobVacancies() {
        List<JobVacancyEntity> jobVacancies = jobVacancyRepository.findAll();

        if (jobVacancies.isEmpty()) {
            throw new NoContentException("No job vacancies registered");
        }

        log(LogEnum.INFO, "List job vacancies: " + jobVacancies.size() + " job vacancies", HttpStatus.OK.value());
        return ResponseEntity.ok(jobVacancies);
    }

    public ResponseEntity<JobVacancyEntity> searchJobVacancyById(UUID id) {
        JobVacancyEntity jobVacancy = jobVacancyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(JOB_VACANCY_NOT_FOUND + id));      

        log(LogEnum.INFO, "Job Vacancy found: " + jobVacancy.getId(), HttpStatus.OK.value());
        return ResponseEntity.ok(jobVacancy);
    }

    public ResponseEntity<Object> deleteJobVacancyById(UUID id, Authentication authentication) {
        if (authentication == null) {
            throw new UnauthorizedUserException(UNAUTHORIZED_ACESS_ATTEMPT);
        }

        JobVacancyEntity jobVacancy = jobVacancyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(JOB_VACANCY_NOT_FOUND + id));

        if (!jobVacancy.equalsCompanyOrAdmin(authentication)) {
            throw new UnauthorizedUserException(UNAUTHORIZED_ACESS_ATTEMPT_DOTS + ((UserEntity) authentication.getPrincipal()).getId());
        }

        jobVacancyRepository.deleteById(id);

        log(LogEnum.INFO, "Job Vacancy deleted: " + id, HttpStatus.NO_CONTENT.value());
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> updateJobVacancy(UUID id, JobVacancyRegisterRequest request, Authentication authentication) {
        if (authentication == null) {
            throw new UnauthorizedUserException(UNAUTHORIZED_ACESS_ATTEMPT);
        }

        JobVacancyEntity jobVacancy = jobVacancyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(JOB_VACANCY_NOT_FOUND + id));

        if (!jobVacancy.equalsCompanyOrAdmin(authentication)) {
            throw new UnauthorizedUserException(UNAUTHORIZED_ACESS_ATTEMPT_DOTS + ((UserEntity) authentication.getPrincipal()).getId());
        }

        jobVacancy.update(request);
        jobVacancyRepository.save(jobVacancy);

        log(LogEnum.INFO, "Job Vacancy updated: " + jobVacancy.getId(), HttpStatus.NO_CONTENT.value());
        return ResponseEntity.noContent().build();
    }
    
}
