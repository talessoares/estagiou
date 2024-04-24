package com.lab.estagiou.service.empresa;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.lab.estagiou.controller.dto.request.auth.RequestCadastroEmpresa;
import com.lab.estagiou.controller.dto.response.error.ErrorResponse;
import com.lab.estagiou.model.entity.Empresa;
import com.lab.estagiou.model.entity.Usuario;
import com.lab.estagiou.model.repository.EmpresaRepository;
import com.lab.estagiou.service.impl.ServiceUsuarioExistsAuthAdmin;

@Service
public class EmpresaService extends ServiceUsuarioExistsAuthAdmin {

    @Autowired
    private EmpresaRepository empresaRepository;

    public ResponseEntity<Object> register(RequestCadastroEmpresa request, Authentication authentication) {
        if (!isAdmin(authentication)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Usuário não autorizado"));
        }

        if (usuarioExists(request)) {
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Email já cadastrado"));
        }

        if (empresaRepository.existsByCnpj(request.getCnpj())) {
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "CNPJ já cadastrado"));
        }

        try {
            Usuario empresa = new Empresa(request);
            usuarioRepository.save(empresa);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
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
