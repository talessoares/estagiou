package com.lab.estagiou.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "empresa")
@Table(name = "tb_empresa")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String email;

    private String senha;

    private String cnpj;

    private String responsavel;

    @OneToMany(mappedBy = "empresa")
    private List<Vaga> vagas = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    
}
