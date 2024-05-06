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
import com.lab.estagiou.model.student.exception.NoStudentFoundException;
import com.lab.estagiou.model.student.exception.NoStudentsRegisteredException;
import com.lab.estagiou.model.student.exception.RegisterStudentException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StudentExceptionHandler extends HandlerExceptionUtil {

    @ExceptionHandler(value = RegisterStudentException.class)
    public ResponseEntity<Object> handleRegisterStudentException(Exception e, HttpServletRequest request) {
        logger(LogEnum.WARN, e.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request));
    }

    @ExceptionHandler(value = NoStudentsRegisteredException.class)
    public ResponseEntity<Object> handleNoStudentsRegisteredException(Exception e, HttpServletRequest request) {
        logger(LogEnum.INFO, e.getMessage());
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(value = NoStudentFoundException.class)
    public ResponseEntity<Object> handleNoStudentFoundException(Exception e, HttpServletRequest request) {
        logger(LogEnum.INFO, e.getMessage());
        return ResponseEntity.noContent().build();
    }
    
}
