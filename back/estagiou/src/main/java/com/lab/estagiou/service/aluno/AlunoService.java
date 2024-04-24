package com.lab.estagiou.service.aluno;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lab.estagiou.controller.dto.request.auth.RequestCadastroAluno;
import com.lab.estagiou.controller.dto.response.error.ErrorResponse;
import com.lab.estagiou.model.entity.Aluno;
import com.lab.estagiou.model.entity.Usuario;
import com.lab.estagiou.model.repository.AlunoRepository;
import com.lab.estagiou.service.impl.ServiceUsuarioExists;

@Service
public class AlunoService extends ServiceUsuarioExists {

    @Autowired
    private AlunoRepository alunoRepository;

    public ResponseEntity<Object> register(RequestCadastroAluno request) {
        if (super.usuarioExists(request)) {
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Email já cadastrado"));
        }

        try {
            Usuario aluno = new Aluno(request);
            super.usuarioRepository.save(aluno);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    public ResponseEntity<List<Aluno>> listarAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();

        if (alunos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(alunos);
    }

    public ResponseEntity<Aluno> buscarAlunoPorId(UUID id) {
        Aluno aluno = alunoRepository.findById(id).orElse(null);

        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(aluno);
    }

    public ResponseEntity<Object> deletarAluno(UUID id) {
        if (!alunoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        alunoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> atualizarAluno(UUID id, RequestCadastroAluno requestCadastroAluno) {
        Aluno aluno = alunoRepository.findById(id).orElse(null);

        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }

        aluno.update(requestCadastroAluno);

        alunoRepository.save(aluno);

        return ResponseEntity.noContent().build();
    }
    
}
