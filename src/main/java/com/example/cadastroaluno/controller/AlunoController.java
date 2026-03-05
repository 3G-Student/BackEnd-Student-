package com.example.cadastroaluno.controller;

import com.example.cadastroaluno.dto.request.AlunoRequestDTO;
import com.example.cadastroaluno.dto.response.AlunoResponseDTO;
import com.example.cadastroaluno.dto.response.DisciplinaResponseDTO;
import com.example.cadastroaluno.service.AlunoService;
import com.example.cadastroaluno.validation.OnCreate;
import com.example.cadastroaluno.validation.OnPatch;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Aluno")
@AllArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    // Métodos comuns
    @GetMapping("buscarPorId/{id}")
    public ResponseEntity<AlunoResponseDTO> buscarAlunoPorId(@PathVariable Integer id) {
        AlunoResponseDTO aluno = alunoService.buscarPorId(id);
        return ResponseEntity.ok(aluno);
    }

    @GetMapping("/listar")
    public List<AlunoResponseDTO> listarAluno(){
        return alunoService.listarAluno();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> inserirAluno(@Validated({OnCreate.class, Default.class})
                                                   @RequestBody AlunoRequestDTO dto) {
        alunoService.cadastrarAluno(dto);
        return ResponseEntity.ok("Aluno inserido com sucesso!");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarAluno(@PathVariable Integer id,
                                                    @Validated({OnPatch.class, Default.class})
                                                    @RequestBody AlunoRequestDTO dto) {
        alunoService.atualizarAluno(id, dto);
        return ResponseEntity.ok("Aluno atualizado com sucesso!");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirAluno(@PathVariable Integer id) {
        alunoService.excluirAluno(id);
        return ResponseEntity.ok("Aluno excluído com sucesso!");
    }

    @GetMapping("/alunosAtivos")
    public ResponseEntity<List<AlunoResponseDTO>> listarPorAtivo(
            @RequestParam Boolean ativo) {

        return ResponseEntity.ok(alunoService.listarPorAtivo(ativo));
    }

    //Query
    @GetMapping("/buscarDisciplinasPorIdAluno/{id}")
    public ResponseEntity<List<DisciplinaResponseDTO>> listarDisciplinas(
            @PathVariable Integer id) {
        List<DisciplinaResponseDTO> disciplinas = alunoService.listarDisciplinasPorAluno(id);
        return ResponseEntity.ok(disciplinas);
    }

}
