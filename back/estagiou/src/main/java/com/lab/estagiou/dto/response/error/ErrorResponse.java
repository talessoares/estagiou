package com.lab.estagiou.dto.response.error;

import java.time.Instant;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

@Data
public class ErrorResponse {

    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(int status, String message, HttpServletRequest request) {
        this.timestamp = Instant.now();
        this.status = status;
        this.error = "Erro";
        this.message = message;
        this.path = request.getRequestURI();
    }

}
