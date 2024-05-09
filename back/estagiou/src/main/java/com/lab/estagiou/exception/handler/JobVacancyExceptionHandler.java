package com.lab.estagiou.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lab.estagiou.dto.response.error.ErrorResponse;
import com.lab.estagiou.exception.handler.util.HandlerExceptionUtil;
import com.lab.estagiou.model.jobvacancy.exception.NoJobVacanciesRegisteredException;
import com.lab.estagiou.model.jobvacancy.exception.NoJobVacancyFoundException;
import com.lab.estagiou.model.jobvacancy.exception.RegisterJobVacancyException;
import com.lab.estagiou.model.jobvacancy.exception.UpdateJobVacancyException;
import com.lab.estagiou.model.log.LogEnum;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JobVacancyExceptionHandler extends HandlerExceptionUtil {

    @ExceptionHandler(value = NoJobVacanciesRegisteredException.class)
    public ResponseEntity<Object> handleNoJobVacanciesRegisteredException(Exception e, HttpServletRequest request) {
        logger(LogEnum.INFO, e.getMessage(), HttpStatus.NO_CONTENT.value(), request);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(value = NoJobVacancyFoundException.class)
    public ResponseEntity<Object> handleNoJobVacancyFoundException(Exception e, HttpServletRequest request) {
        logger(LogEnum.INFO, e.getMessage(), HttpStatus.NO_CONTENT.value(), request);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(value = UpdateJobVacancyException.class)
    public ResponseEntity<Object> handleUpdateJobVacancyException(Exception e, HttpServletRequest request) {
        logger(LogEnum.INFO, e.getMessage(), HttpStatus.BAD_REQUEST.value(), request);
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request));
    }

    @ExceptionHandler(value = RegisterJobVacancyException.class)
    public ResponseEntity<Object> handleRegisterJobVacancyException(Exception e, HttpServletRequest request) {
        logger(LogEnum.INFO, e.getMessage(), HttpStatus.BAD_REQUEST.value(), request);
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request));
    }
    
}
