package com.lab.estagiou.service.aluno;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lab.estagiou.controller.dto.request.auth.RequestCadastro;
import com.lab.estagiou.model.entity.Aluno;
import com.lab.estagiou.model.repository.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

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

    public ResponseEntity<Object> atualizarAluno(UUID id, RequestCadastro requestCadastroAluno) {
        Aluno aluno = alunoRepository.findById(id).orElse(null);

        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }

        aluno.update(requestCadastroAluno);

        alunoRepository.save(aluno);

        return ResponseEntity.noContent().build();
    }
    
}
