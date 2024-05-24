package com.lab.estagiou.exception.handler.util;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.lab.estagiou.model.log.LogEntity;
import com.lab.estagiou.model.log.LogEnum;
import com.lab.estagiou.model.log.LogRepository;

import jakarta.servlet.http.HttpServletRequest;

public abstract class HandlerExceptionUtil {

    @Autowired
    private LogRepository logRepository;

    @Value("${logs.show.enable}")
    private boolean logsShowEnabled;

    @Value("${logs.save.enable}")
    private boolean logsSaveEnabled;

    public static final Logger logger = LoggerFactory.getLogger(HandlerExceptionUtil.class);

    public void log(LogEnum level, String message, int status, HttpServletRequest request) {
        if (!logsShowEnabled && !logsSaveEnabled) {
            return;
        }

        if (logsSaveEnabled) {
            String path = request == null ? null : request.getRequestURI();
            saveLog(level, status, message, path);
        }

        if (logsShowEnabled) {
            showLog(level, message);
        }
    }

    public void log(LogEnum level, String message, int status) {
        log(level, message, status, null);
    }

    private void saveLog(LogEnum level, int status, String message, String path) {
        LogEntity log = new LogEntity(level, status, message, Instant.now(), path);
        logRepository.save(log);
    }

    private void showLog(LogEnum level, String message) {
        switch (level) {
            case INFO:
                logger.info(message);
                break;
            case WARN:
                logger.warn(message);
                break;
            case ERROR:
                logger.error(message);
                break;
            default:
                logger.info(message);
                break;
        }
    }
    
}
