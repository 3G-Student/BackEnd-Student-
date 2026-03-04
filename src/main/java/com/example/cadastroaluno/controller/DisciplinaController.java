package com.example.cadastroaluno.controller;

import com.example.cadastroaluno.dto.request.DisciplinaRequestDTO;
import com.example.cadastroaluno.dto.response.DisciplinaResponseDTO;
import com.example.cadastroaluno.service.DisciplinaService;
import com.example.cadastroaluno.validation.OnCreate;
import com.example.cadastroaluno.validation.OnPatch;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Disciplina")
@AllArgsConstructor
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    //    Métodos comuns
    @GetMapping("buscarPorId/{id}")
    public ResponseEntity<DisciplinaResponseDTO> buscarDisciplinaPorId(@PathVariable Integer id) {
        DisciplinaResponseDTO disciplina = disciplinaService.buscarPorId(id);
        return ResponseEntity.ok(disciplina);
    }

    @GetMapping("/listar")
    public List<DisciplinaResponseDTO> listarDisciplina(){
        return disciplinaService.listarDisciplina();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> inserirDisciplina(@Validated({OnCreate.class, Default.class})
                                                 @RequestBody DisciplinaRequestDTO dto) {
        disciplinaService.cadastrarDisciplina(dto);
        return ResponseEntity.ok("Disciplina inserido com sucesso!");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarDisciplina(@PathVariable Integer id,
                                                    @Validated({OnPatch.class, Default.class})
                                                    @RequestBody DisciplinaRequestDTO dto) {
        disciplinaService.atualizarDisciplina(id, dto);
        return ResponseEntity.ok("Disciplina atualizado com sucesso!");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirDisciplina(@PathVariable Integer id) {
        disciplinaService.excluirDisciplina(id);
        return ResponseEntity.ok("Disciplina excluído com sucesso!");
    }

}
