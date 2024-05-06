package com.lab.estagiou.service.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lab.estagiou.dto.response.error.ErrorResponse;
import com.lab.estagiou.model.log.LogEnum;
import com.lab.estagiou.service.util.UtilService;

@ControllerAdvice
public class ExceptionErrorHandler extends UtilService {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        logger(LogEnum.ERROR, "Internal error: " + e.getMessage());
        return ResponseEntity.internalServerError().body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno"));
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(Exception e) {
        logger(LogEnum.ERROR, "Illegal argument: " + e.getMessage());
        return ResponseEntity.internalServerError().body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }
    
}
