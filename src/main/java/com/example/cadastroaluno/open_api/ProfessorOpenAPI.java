package com.example.cadastroaluno.open_api;

import com.example.cadastroaluno.dto.request.ProfessorRequestDTO;
import com.example.cadastroaluno.dto.response.AlunoRecuperacaoResponseDTO;
import com.example.cadastroaluno.dto.response.ProfessorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProfessorOpenAPI {

    @Operation(summary = "Buscar professor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor encontrado"),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    })
    @GetMapping("/buscarPorId/{id}")
    ResponseEntity<ProfessorResponseDTO> buscarProfessorPorId(@PathVariable Integer id);


    @Operation(summary = "Listar todos os professores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/listar")
    List<ProfessorResponseDTO> listarProfessor();


    @Operation(summary = "Cadastrar novo professor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/cadastrar")
    ResponseEntity<String> inserirProfessor(@RequestBody ProfessorRequestDTO dto);


    @Operation(summary = "Atualizar professor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    ResponseEntity<String> atualizarProfessor(
            @PathVariable Integer id,
            @RequestBody ProfessorRequestDTO dto
    );


    @Operation(summary = "Excluir professor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    })
    @DeleteMapping("/excluir/{id}")
    ResponseEntity<String> excluirProfessor(@PathVariable Integer id);


    @Operation(summary = "Listar alunos em recuperação de um professor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/buscarAlunosDeRecuperacaoPorIdProfessor/{id}")
    ResponseEntity<List<AlunoRecuperacaoResponseDTO>> listarAlunosDeRecuperacao(
            @PathVariable Integer id
    );
}