package com.lab.estagiou.controller.aluno;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab.estagiou.controller.dto.request.auth.RequestCadastroAluno;
import com.lab.estagiou.model.entity.Aluno;
import com.lab.estagiou.service.aluno.AlunoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping(value = "/v1/aluno", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Aluno", description = "API para gerenciamento de alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Operation(summary = "Registrar aluno", description = "Registra um aluno")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Aluno registrado com sucesso", content = @Content),
        @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<Object> registerAluno(@RequestBody RequestCadastroAluno request) {
        return alunoService.register(request);
    }

    @Operation(summary = "Listar alunos", description = "Lista todos os alunos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Alunos listados com sucesso"),
        @ApiResponse(responseCode = "204", description = "Nenhum aluno encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @GetMapping(path = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Aluno>> listarAlunos() {
        return alunoService.listarAlunos();
    }
    
    @Operation(summary = "Buscar aluno por ID", description = "Busca um aluno por Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aluno encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable UUID id) {
        return alunoService.buscarAlunoPorId(id);
    }

    @Operation(summary = "Deletar aluno", description = "Deleta um aluno")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Aluno deletado com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarAluno(@PathVariable UUID id) {
        return alunoService.deletarAluno(id);
    }

    @Operation(summary = "Atualizar aluno", description = "Atualiza um aluno")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Aluno atualizado com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarAluno(@PathVariable UUID id, @RequestBody RequestCadastroAluno requestCadastro) {
        return alunoService.atualizarAluno(id, requestCadastro);
    }

}
