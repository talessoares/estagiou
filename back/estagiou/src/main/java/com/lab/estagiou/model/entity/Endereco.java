package com.lab.estagiou.model.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "endereco")
@Table(name = "tb_endereco")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String pais;

    private String uf;

    private String municipio;

    private String bairro;

    private String logradouro;

    private String numero;

    private String complemento;
    
}
