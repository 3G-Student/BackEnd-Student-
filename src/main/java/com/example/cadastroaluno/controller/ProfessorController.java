package com.example.cadastroaluno.controller;

import com.example.cadastroaluno.dto.request.ProfessorRequestDTO;
import com.example.cadastroaluno.dto.response.ProfessorResponseDTO;
import com.example.cadastroaluno.service.ProfessorService;
import com.example.cadastroaluno.validation.OnCreate;
import com.example.cadastroaluno.validation.OnPatch;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Professor")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService service) {
        this.professorService = service;
    }

    //    Métodos comuns
    @GetMapping("buscarPorId/{id}")
    public ResponseEntity<ProfessorResponseDTO> buscarProfessorPorId(@PathVariable Integer id) {
        ProfessorResponseDTO professor = professorService.buscarPorId(id);
        return ResponseEntity.ok(professor);
    }

    @GetMapping("/listar")
    public List<ProfessorResponseDTO> listarProfessor(){
        return professorService.listarProfessor();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> inserirProfessor(@Validated({OnCreate.class, Default.class})
                                                    @RequestBody ProfessorRequestDTO dto) {
        professorService.cadastrarProfessor(dto);
        return ResponseEntity.ok("Professor inserido com sucesso!");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarProfessor(@PathVariable Integer id,
                                                      @Validated({OnPatch.class, Default.class})
                                                      @RequestBody ProfessorRequestDTO dto) {
        professorService.atualizarProfessor(id, dto);
        return ResponseEntity.ok("Professor atualizado com sucesso!");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirProfessor(@PathVariable Integer id) {
        professorService.excluirProfessor(id);
        return ResponseEntity.ok("Professor excluído com sucesso!");
    }
}
