package com.example.cadastroaluno.open_api;

import com.example.cadastroaluno.dto.request.ProfessorDisciplinaRequestDTO;
import com.example.cadastroaluno.dto.response.ProfessorDisciplinaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProfessorDisciplinaOpenAPI {

    @Operation(summary = "Vincular professor a disciplina")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vínculo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/vincular")
    ProfessorDisciplinaResponseDTO vincular(
            @RequestBody ProfessorDisciplinaRequestDTO dto
    );


    @Operation(summary = "Listar todos os vínculos professor-disciplina")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/listar")
    List<ProfessorDisciplinaResponseDTO> listarTodos();


    @Operation(summary = "Remover vínculo entre professor e disciplina")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vínculo removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Vínculo não encontrado")
    })
    @DeleteMapping("/remover")
    ResponseEntity<String> remover(
            @RequestParam Integer professorId,
            @RequestParam Integer disciplinaId
    );
}