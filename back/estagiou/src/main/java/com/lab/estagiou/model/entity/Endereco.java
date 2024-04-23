package com.lab.estagiou.model.entity;

import java.io.Serializable;
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
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public Endereco(String pais, String uf, String municipio, String bairro, String logradouro, String numero,
            String complemento) {

        if (pais == null || pais.isBlank()) {
            throw new IllegalArgumentException("País não pode ser nulo ou vazio");
        }

        if (uf == null || uf.isBlank()) {
            throw new IllegalArgumentException("UF não pode ser nulo ou vazio");
        }

        if (municipio == null || municipio.isBlank()) {
            throw new IllegalArgumentException("Município não pode ser nulo ou vazio");
        }

        if (bairro == null || bairro.isBlank()) {
            throw new IllegalArgumentException("Bairro não pode ser nulo ou vazio");
        }

        if (logradouro == null || logradouro.isBlank()) {
            throw new IllegalArgumentException("Logradouro não pode ser nulo ou vazio");
        }

        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número não pode ser nulo ou vazio");
        }

        this.id = null;
        this.pais = pais;
        this.uf = uf;
        this.municipio = municipio;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
    }
    
}
