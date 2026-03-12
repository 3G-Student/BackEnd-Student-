package com.example.cadastroaluno.controller;

import com.example.cadastroaluno.dto.request.ProfessorDisciplinaRequestDTO;
import com.example.cadastroaluno.dto.response.ProfessorDisciplinaResponseDTO;
import com.example.cadastroaluno.open_api.ProfessorDisciplinaOpenAPI;
import com.example.cadastroaluno.service.ProfessorDisciplinaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professorDisciplina")
@AllArgsConstructor
public class ProfessorDisciplinaController implements ProfessorDisciplinaOpenAPI {

    private final ProfessorDisciplinaService service;

    @PostMapping("/vincular")
    public ProfessorDisciplinaResponseDTO vincular(
            @RequestBody ProfessorDisciplinaRequestDTO dto) {
        return service.vincular(dto);
    }

    @GetMapping("/listar")
    public List<ProfessorDisciplinaResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @DeleteMapping("/remover")
    public ResponseEntity<String> remover(@RequestParam Integer professorId,
                        @RequestParam Integer disciplinaId) {
        service.remover(professorId, disciplinaId);
        return ResponseEntity.ok("Exclusão feita com sucesso!");
    }

}