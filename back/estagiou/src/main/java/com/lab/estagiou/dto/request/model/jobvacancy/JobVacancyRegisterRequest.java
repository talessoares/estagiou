package com.lab.estagiou.dto.request.model.jobvacancy;

import lombok.Data;

@Data
public class JobVacancyRegisterRequest {

    private String title;
    private String role;
    private String description;
    private String salary;
    private String hours;
    private String modality;
    
}
