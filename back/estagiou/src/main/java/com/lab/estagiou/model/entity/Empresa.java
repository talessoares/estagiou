package com.lab.estagiou.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.lab.estagiou.controller.dto.request.empresa.RequestCadastroEmpresa;

import jakarta.persistence.Column;
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

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(unique = true, nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private String responsavel;

    @OneToMany(mappedBy = "empresa")
    private List<Vaga> vagas = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "endereco_id", nullable = true)
    private Endereco endereco;

    private static final String VAGA_NULA = "Vaga não pode ser nula";
    
    public Empresa(String nome, String email, String senha, String cnpj, String responsavel) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome da empresa não pode ser nulo");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email da empresa não pode ser nulo");
        }

        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha da empresa não pode ser nulo");
        }

        if (cnpj == null || cnpj.isBlank()) {
            throw new IllegalArgumentException("CNPJ da empresa não pode ser nulo");
        }

        if (responsavel == null || responsavel.isBlank()) {
            throw new IllegalArgumentException("Responsável pela empresa não pode ser nulo");
        }

        this.id = null;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.responsavel = responsavel;
        this.vagas = new ArrayList<>();
    }

    public Empresa(RequestCadastroEmpresa requestCadastroEmpresa) {
        this(requestCadastroEmpresa.getNome(), requestCadastroEmpresa.getEmail(), requestCadastroEmpresa.getSenha(), requestCadastroEmpresa.getCnpj(), requestCadastroEmpresa.getResponsavel());
    }

    public boolean addVaga(Vaga vaga) {
        if (vaga == null) {
            throw new IllegalArgumentException(VAGA_NULA);
        }

        return this.vagas.add(vaga);
    }

    public boolean removeVaga(Vaga vaga) {
        if (vaga == null) {
            throw new IllegalArgumentException(VAGA_NULA);
        }

        return this.vagas.remove(vaga);
    }

    public boolean containsVaga(Vaga vaga) {
        if (vaga == null) {
            throw new IllegalArgumentException(VAGA_NULA);
        }

        return this.vagas.contains(vaga);
    }

    public boolean isVagasEmpty() {
        return this.vagas.isEmpty();
    }

    public int getQuantidadeVagas() {
        return this.vagas.size();
    }

    public boolean equalsEndereco(Endereco endereco) {
        return this.endereco.equals(endereco);
    }

    public void update(RequestCadastroEmpresa requestCadastroEmpresa) {
        if (requestCadastroEmpresa.getNome() != null && !requestCadastroEmpresa.getNome().isBlank()) {
            this.nome = requestCadastroEmpresa.getNome();
        }

        if (requestCadastroEmpresa.getEmail() != null && !requestCadastroEmpresa.getEmail().isBlank()) {
            this.email = requestCadastroEmpresa.getEmail();
        }

        if (requestCadastroEmpresa.getSenha() != null && !requestCadastroEmpresa.getSenha().isBlank()) {
            this.senha = requestCadastroEmpresa.getSenha();
        }

        if (requestCadastroEmpresa.getCnpj() != null && !requestCadastroEmpresa.getCnpj().isBlank()) {
            this.cnpj = requestCadastroEmpresa.getCnpj();
        }

        if (requestCadastroEmpresa.getResponsavel() != null && !requestCadastroEmpresa.getResponsavel().isBlank()) {
            this.responsavel = requestCadastroEmpresa.getResponsavel();
        }
    }
}
