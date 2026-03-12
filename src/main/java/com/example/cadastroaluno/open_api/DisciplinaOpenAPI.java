package com.example.cadastroaluno.open_api;

import com.example.cadastroaluno.dto.request.DisciplinaRequestDTO;
import com.example.cadastroaluno.dto.response.DisciplinaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface DisciplinaOpenAPI {

    @Operation(summary = "Buscar disciplina por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disciplina encontrada"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada")
    })
    @GetMapping("/buscarPorId/{id}")
    ResponseEntity<DisciplinaResponseDTO> buscarDisciplinaPorId(@PathVariable Integer id);


    @Operation(summary = "Listar todas as disciplinas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/listar")
    List<DisciplinaResponseDTO> listarDisciplina();


    @Operation(summary = "Cadastrar nova disciplina")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disciplina cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/cadastrar")
    ResponseEntity<String> inserirDisciplina(@RequestBody DisciplinaRequestDTO dto);


    @Operation(summary = "Atualizar disciplina")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disciplina atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada")
    })
    @PutMapping("/atualizar/{id}")
    ResponseEntity<String> atualizarDisciplina(
            @PathVariable Integer id,
            @RequestBody DisciplinaRequestDTO dto
    );


    @Operation(summary = "Excluir disciplina")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disciplina excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada")
    })
    @DeleteMapping("/excluir/{id}")
    ResponseEntity<String> excluirDisciplina(@PathVariable Integer id);

}