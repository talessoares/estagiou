package com.lab.estagiou.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lab.estagiou.dto.response.error.ErrorResponse;
import com.lab.estagiou.exception.generic.UnauthorizedUserException;
import com.lab.estagiou.exception.handler.util.HandlerExceptionUtil;
import com.lab.estagiou.model.log.LogEnum;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UnauthorizedUserHandler extends HandlerExceptionUtil {

    @ExceptionHandler(value = UnauthorizedUserException.class)
    public ResponseEntity<Object> handleUnauthorizedUserException(Exception e, HttpServletRequest request) {
        log(LogEnum.WARN, e.getMessage(), HttpStatus.FORBIDDEN.value(), request);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Usuário não autorizado", request));
    }
    
}
