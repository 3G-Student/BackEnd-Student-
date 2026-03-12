package com.example.cadastroaluno.open_api;

import com.example.cadastroaluno.dto.request.BoletimRequestDTO;
import com.example.cadastroaluno.dto.response.BoletimResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BoletimOpenAPI {

    @Operation(summary = "Buscar boletim por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boletim encontrado"),
            @ApiResponse(responseCode = "404", description = "Boletim não encontrado")
    })
    @GetMapping("/buscarPorId/{id}")
    ResponseEntity<BoletimResponseDTO> buscarBoletimPorId(@PathVariable Integer id);


    @Operation(summary = "Listar todos os boletins")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de boletins retornada com sucesso")
    })
    @GetMapping("/listar")
    List<BoletimResponseDTO> listarBoletim();


    @Operation(summary = "Cadastrar novo boletim")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boletim cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/cadastrar")
    ResponseEntity<String> inserirBoletim(@RequestBody BoletimRequestDTO dto);


    @Operation(summary = "Atualizar boletim")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boletim atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Boletim não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    ResponseEntity<String> atualizarBoletim(
            @PathVariable Integer id,
            @RequestBody BoletimRequestDTO dto
    );


    @Operation(summary = "Excluir boletim")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boletim excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Boletim não encontrado")
    })
    @DeleteMapping("/excluir/{id}")
    ResponseEntity<String> excluirBoletim(@PathVariable Integer id);


    @Operation(summary = "Listar boletins por ID do aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de boletins retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    @GetMapping("/buscarBoletimPorIdAluno/{id}")
    ResponseEntity<List<BoletimResponseDTO>> listarObsPorIdAluno(
            @PathVariable Integer id
    );
}