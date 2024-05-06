package com.lab.estagiou.model.log;

public enum LogEnum {

    INFO("INFO"),
    WARN("WARN"),
    ERROR("ERROR");

    private final String value;

    LogEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LogEnum fromValue(String value) {
        for (LogEnum logEnum : LogEnum.values()) {
            if (logEnum.getValue().equals(value)) {
                return logEnum;
            }
        }

        return null;
    }
    
}
