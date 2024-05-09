package com.lab.estagiou.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lab.estagiou.dto.response.error.ErrorResponse;
import com.lab.estagiou.exception.handler.util.HandlerExceptionUtil;
import com.lab.estagiou.model.log.LogEnum;
import com.lab.estagiou.model.user.exception.RegisterUserException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserExceptionHandler extends HandlerExceptionUtil {

    @ExceptionHandler(value = RegisterUserException.class)
    public ResponseEntity<Object> handleRegisterStudentException(Exception e, HttpServletRequest request) {
        logger(LogEnum.WARN, e.getMessage(), HttpStatus.BAD_REQUEST.value(), request);
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request));
    }
    
}
