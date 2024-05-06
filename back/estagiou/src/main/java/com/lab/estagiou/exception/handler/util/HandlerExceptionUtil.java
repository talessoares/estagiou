package com.lab.estagiou.exception.handler.util;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.lab.estagiou.model.log.LogEntity;
import com.lab.estagiou.model.log.LogEnum;
import com.lab.estagiou.model.log.LogRepository;

public abstract class HandlerExceptionUtil {

    @Autowired
    private LogRepository logRepository;

    @Value("${logs.show.enabled}")
    private boolean logsShowEnabled;

    @Value("${logs.save.enabled}")
    private boolean logsSaveEnabled;

    public static final Logger logger = LoggerFactory.getLogger(HandlerExceptionUtil.class);

    public void logger(LogEnum level, String message) {
        if (!logsShowEnabled && !logsSaveEnabled) {
            return;
        }

        if (logsSaveEnabled) {
            saveLog(level, message);
        }

        if (logsShowEnabled) {
            showLog(level, message);
        }
    }

    private void saveLog(LogEnum level, String message) {
        LogEntity log = new LogEntity(null, level, message, Instant.now());
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
