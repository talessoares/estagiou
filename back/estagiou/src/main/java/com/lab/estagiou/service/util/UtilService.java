package com.lab.estagiou.service.util;

import java.time.Instant;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import com.lab.estagiou.dto.request.model.util.RequestRegister;
import com.lab.estagiou.model.log.LogEntity;
import com.lab.estagiou.model.log.LogEnum;
import com.lab.estagiou.model.log.LogRepository;
import com.lab.estagiou.model.user.UserEntity;
import com.lab.estagiou.model.user.UserRepository;
import com.lab.estagiou.model.user.UserRoleEnum;

public abstract class UtilService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private LogRepository logRepository;

    @Value("${logs.show.enabled}")
    private boolean logsShowEnabled;

    @Value("${logs.save.enabled}")
    private boolean logsSaveEnabled;

    public static final Logger logger = LoggerFactory.getLogger(UtilService.class);

    public boolean userExists(RequestRegister request) {
        return userRepository.findByEmail(request.getEmail()) != null;
    }

    public boolean userIsSameOrAdmin(Authentication authentication, UUID id) {
        if (authentication == null) {
            return false;
        }

        UserEntity user = (UserEntity) authentication.getPrincipal();
        return user.getId().equals(id) || user.getRole().equals(UserRoleEnum.ADMIN);
    }

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
