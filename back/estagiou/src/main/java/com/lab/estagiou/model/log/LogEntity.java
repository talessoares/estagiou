package com.lab.estagiou.model.log;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "log")
@Table(name = "tb_log")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private LogEnum level;

    private int status;

    private String message;

    private Instant timestamp;

    private String path;

    public LogEntity(LogEnum level, int status, String message, Instant timestamp, String path) {
        this.level = level;
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
    }

}
