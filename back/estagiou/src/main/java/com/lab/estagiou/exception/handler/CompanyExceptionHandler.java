package com.lab.estagiou.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lab.estagiou.dto.response.error.ErrorResponse;
import com.lab.estagiou.exception.handler.util.HandlerExceptionUtil;
import com.lab.estagiou.model.company.exception.CnpjAlreadyRegisteredException;
import com.lab.estagiou.model.company.exception.NoCompaniesRegisteredException;
import com.lab.estagiou.model.company.exception.NoCompanyFoundException;
import com.lab.estagiou.model.company.exception.RegisterCompanyException;
import com.lab.estagiou.model.log.LogEnum;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CompanyExceptionHandler extends HandlerExceptionUtil {

    @ExceptionHandler(value = RegisterCompanyException.class)
    public ResponseEntity<Object> handleRegisterCompanyException(Exception e, HttpServletRequest request) {
        logger(LogEnum.WARN, e.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request));
    }

    @ExceptionHandler(value = CnpjAlreadyRegisteredException.class)
    public ResponseEntity<Object> handleCnpjAlreadyRegisteredException(Exception e, HttpServletRequest request) {
        logger(LogEnum.WARN, e.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "CNPJ já cadastrado", request));
    }

    @ExceptionHandler(value = NoCompaniesRegisteredException.class)
    public ResponseEntity<Object> handleNoCompaniesRegisteredException(Exception e, HttpServletRequest request) {
        logger(LogEnum.INFO, e.getMessage());
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(value = NoCompanyFoundException.class)
    public ResponseEntity<Object> handleNoCompanyFoundException(Exception e, HttpServletRequest request) {
        logger(LogEnum.INFO, e.getMessage());
        return ResponseEntity.noContent().build();
    }

}
