package com.lab.estagiou.controller.empresa;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab.estagiou.controller.dto.request.auth.RequestCadastroEmpresa;
import com.lab.estagiou.model.entity.Empresa;
import com.lab.estagiou.service.empresa.EmpresaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping(value = "/v1/empresa", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Empresa", description = "API para gerenciamento de empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerEmpresa(@RequestBody RequestCadastroEmpresa request) {
        return empresaService.register(request);
    }

    @Operation(summary = "Listar empresa", description = "Lista todas as empresas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empresas listadas com sucesso"),
        @ApiResponse(responseCode = "204", description = "Nenhuma empresa encontrada", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @GetMapping(path = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        return empresaService.listarEmpresas();
    }
    
    @Operation(summary = "Buscar empresa por ID", description = "Busca uma empresa por Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empresa encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Empresa não encontrada", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Empresa> buscarEmpresaPorId(@PathVariable UUID id) {
        return empresaService.buscarEmpresaPorId(id);
    }

    @Operation(summary = "Deletar empresa", description = "Deleta uma empresa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Empresa deletada com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Empresa não encontrada", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarEmpresa(@PathVariable UUID id) {
        return empresaService.deletarEmpresa(id);
    }

    @Operation(summary = "Atualizar empresa", description = "Atualiza uma empresa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Empresa atualizada com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Empresa não encontrada", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarEmpresa(@PathVariable UUID id, @RequestBody RequestCadastroEmpresa requestCadastroEmpresa) {
        return empresaService.atualizarEmpresa(id, requestCadastroEmpresa);
    }

}
