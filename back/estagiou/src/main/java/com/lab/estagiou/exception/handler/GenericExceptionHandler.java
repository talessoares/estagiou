package com.lab.estagiou.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lab.estagiou.dto.response.error.ErrorResponse;
import com.lab.estagiou.exception.generic.EmailAlreadyRegisteredException;
import com.lab.estagiou.exception.generic.UnauthorizedUserException;
import com.lab.estagiou.exception.handler.util.HandlerExceptionUtil;
import com.lab.estagiou.model.log.LogEnum;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GenericExceptionHandler extends HandlerExceptionUtil {

    @ExceptionHandler(value = EmailAlreadyRegisteredException.class)
    public ResponseEntity<Object> handleEmailAlreadyRegisteredException(Exception e, HttpServletRequest request) {
        logger(LogEnum.WARN, e.getMessage(), HttpStatus.BAD_REQUEST.value(), request);
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Email já cadastrado", request));
    }

    @ExceptionHandler(value = UnauthorizedUserException.class)
    public ResponseEntity<Object> handleUnauthorizedUserException(Exception e, HttpServletRequest request) {
        logger(LogEnum.WARN, e.getMessage(), HttpStatus.FORBIDDEN.value(), request);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Usuário não autorizado", request));
    }
    
}
