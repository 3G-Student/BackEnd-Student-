package com.example.cadastroaluno.open_api;

import com.example.cadastroaluno.dto.request.AlunoRequestDTO;
import com.example.cadastroaluno.dto.response.AlunoResponseDTO;
import com.example.cadastroaluno.dto.response.DisciplinaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AlunoOpenAPI {

    @Operation(summary = "Buscar aluno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno encontrado"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    @GetMapping("/buscarPorId/{id}")
    ResponseEntity<AlunoResponseDTO> buscarAlunoPorId(@PathVariable Integer id);


    @Operation(summary = "Listar todos os alunos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de alunos retornada com sucesso")
    })
    @GetMapping("/listar")
    List<AlunoResponseDTO> listarAluno();


    @Operation(summary = "Cadastrar novo aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/cadastrar")
    ResponseEntity<String> inserirAluno(@RequestBody AlunoRequestDTO dto);


    @Operation(summary = "Atualizar aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    ResponseEntity<String> atualizarAluno(
            @PathVariable Integer id,
            @RequestBody AlunoRequestDTO dto
    );


    @Operation(summary = "Excluir aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    @DeleteMapping("/excluir/{id}")
    ResponseEntity<String> excluirAluno(@PathVariable Integer id);


    @Operation(summary = "Listar alunos por status ativo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/alunosAtivos")
    ResponseEntity<List<AlunoResponseDTO>> listarPorAtivo(
            @RequestParam Boolean ativo
    );


    @Operation(summary = "Listar disciplinas de um aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de disciplinas retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    @GetMapping("/buscarDisciplinasPorIdAluno/{id}")
    ResponseEntity<List<DisciplinaResponseDTO>> listarDisciplinas(
            @PathVariable Integer id
    );
}