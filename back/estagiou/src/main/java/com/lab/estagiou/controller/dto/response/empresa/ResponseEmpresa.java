package com.lab.estagiou.controller.dto.response.empresa;

import java.util.List;

import com.lab.estagiou.model.entity.Empresa;
import com.lab.estagiou.model.entity.Endereco;
import com.lab.estagiou.model.entity.Vaga;

import lombok.Data;

@Data
public class ResponseEmpresa {

    private String nome;
    private String email;
    private String senha;
    private String cnpj;
    private String responsavel;
    private List<Vaga> vagas;
    private Endereco endereco;

    public ResponseEmpresa(Empresa empresa) {
        this.nome = empresa.getNome();
        this.email = empresa.getEmail();
        this.senha = empresa.getSenha();
        this.cnpj = empresa.getCnpj();
        this.responsavel = empresa.getResponsavel();
        this.vagas = empresa.getVagas();
        this.endereco = empresa.getEndereco();
    }
    
}
