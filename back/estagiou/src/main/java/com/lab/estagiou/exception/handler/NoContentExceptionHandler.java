package com.lab.estagiou.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lab.estagiou.dto.response.error.ErrorResponse;
import com.lab.estagiou.exception.generic.NoContentException;
import com.lab.estagiou.exception.handler.util.HandlerExceptionUtil;
import com.lab.estagiou.model.log.LogEnum;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class NoContentExceptionHandler extends HandlerExceptionUtil {

    @ExceptionHandler(value = NoContentException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception e, HttpServletRequest request) {
        log(LogEnum.WARN, e.getClass().getSimpleName() + ": " + e.getMessage(), HttpStatus.NO_CONTENT.value(), request);
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.NO_CONTENT.value(), e.getMessage(), request));
    }
    
}
