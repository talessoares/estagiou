package com.lab.estagiou.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lab.estagiou.exception.handler.util.HandlerExceptionUtil;
import com.lab.estagiou.model.log.LogEnum;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class RuntimeExceptionExceptionHandler extends HandlerExceptionUtil {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception e, HttpServletRequest request) {
        log(LogEnum.INFO, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), request);
        return ResponseEntity.noContent().build();
    }
    
}