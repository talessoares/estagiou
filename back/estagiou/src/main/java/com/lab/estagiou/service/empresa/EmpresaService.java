package com.lab.estagiou.service.empresa;

import java.net.URI;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lab.estagiou.controller.dto.request.empresa.RequestCadastroEmpresa;
import com.lab.estagiou.controller.dto.response.error.ErrorResponse;
import com.lab.estagiou.model.entity.Empresa;
import com.lab.estagiou.model.repository.EmpresaRepository;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public ResponseEntity<ErrorResponse> cadastrarEmpresa(RequestCadastroEmpresa request) {
        if (empresaRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Email já existente"));
        }

        if (empresaRepository.existsByCnpj(request.getCnpj())) {
            return ResponseEntity.badRequest().body(new ErrorResponse("CNPJ já existente"));
        }

        Empresa empresa = new Empresa(request);

        empresa = empresaRepository.save(empresa);

        URI uri = URI.create("/v1/empresa/" + empresa.getId());

        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<List<Empresa>> listarEmpresas() {
        List<Empresa> empresas = empresaRepository.findAll();

        if (empresas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(empresas);
    }

    public ResponseEntity<Empresa> buscarEmpresaPorId(UUID id) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);

        if (empresa == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(empresa);
    }

    public ResponseEntity<Object> deletarEmpresa(UUID id) {
        if (!empresaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        empresaRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> atualizarEmpresa(UUID id, RequestCadastroEmpresa requestCadastroEmpresa) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);

        if (empresa == null) {
            return ResponseEntity.notFound().build();
        }

        empresa.update(requestCadastroEmpresa);

        empresaRepository.save(empresa);

        return ResponseEntity.noContent().build();
    }
    
}