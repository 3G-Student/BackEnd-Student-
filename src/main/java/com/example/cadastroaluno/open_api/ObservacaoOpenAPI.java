package com.example.cadastroaluno.open_api;

import com.example.cadastroaluno.dto.request.ObservacaoRequestDTO;
import com.example.cadastroaluno.dto.request.ObservacaoUpdateRequestDTO;
import com.example.cadastroaluno.dto.response.ObservacaoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ObservacaoOpenAPI {

    @Operation(summary = "Buscar observação por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Observação encontrada"),
            @ApiResponse(responseCode = "404", description = "Observação não encontrada")
    })
    @GetMapping("/buscarPorId/{id}")
    ResponseEntity<ObservacaoResponseDTO> buscarObservacaoPorId(@PathVariable Integer id);


    @Operation(summary = "Listar todas as observações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/listar")
    List<ObservacaoResponseDTO> listarObservacao();


    @Operation(summary = "Cadastrar nova observação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Observação cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/cadastrar")
    ResponseEntity<String> inserirObservacao(@RequestBody ObservacaoRequestDTO dto);


    @Operation(summary = "Atualizar observação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Observação atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Observação não encontrada")
    })
    @PutMapping("/atualizar/{id}")
    ResponseEntity<String> atualizarObservacao(
            @PathVariable Integer id,
            @RequestBody ObservacaoUpdateRequestDTO dto
    );


    @Operation(summary = "Excluir observação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Observação excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Observação não encontrada")
    })
    @DeleteMapping("/excluir/{id}")
    ResponseEntity<String> excluirObservacao(@PathVariable Integer id);


    @Operation(summary = "Listar observações por ID do aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/buscarObservacoesPorIdAluno/{id}")
    ResponseEntity<List<ObservacaoResponseDTO>> listarObsPorIdAluno(
            @PathVariable Integer id
    );


    @Operation(summary = "Listar observações por ID do professor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/buscarObservacoesPorIdProfessor/{id}")
    ResponseEntity<List<ObservacaoResponseDTO>> listarObsPorIdProfessor(
            @PathVariable Integer id
    );

}