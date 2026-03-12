package com.example.cadastroaluno.open_api;

import com.example.cadastroaluno.dto.request.SecretarioAdmRequestDTO;
import com.example.cadastroaluno.dto.response.SecretarioAdmResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface SecretarioAdmOpenAPI {

    @Operation(summary = "Buscar secretário administrativo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Secretário encontrado"),
            @ApiResponse(responseCode = "404", description = "Secretário não encontrado")
    })
    @GetMapping("/buscarPorId/{id}")
    ResponseEntity<SecretarioAdmResponseDTO> buscarSecretarioAdmPorId(@PathVariable Integer id);


    @Operation(summary = "Listar todos os secretários administrativos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/listar")
    List<SecretarioAdmResponseDTO> listarSecretarioAdm();


    @Operation(summary = "Cadastrar novo secretário administrativo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Secretário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/cadastrar")
    ResponseEntity<String> inserirSecretarioAdm(
            @RequestBody SecretarioAdmRequestDTO dto
    );


    @Operation(summary = "Atualizar secretário administrativo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Secretário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Secretário não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    ResponseEntity<String> atualizarSecretarioAdm(
            @PathVariable Integer id,
            @RequestBody SecretarioAdmRequestDTO dto
    );


    @Operation(summary = "Excluir secretário administrativo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Secretário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Secretário não encontrado")
    })
    @DeleteMapping("/excluir/{id}")
    ResponseEntity<String> excluirSecretarioAdm(@PathVariable Integer id);

}