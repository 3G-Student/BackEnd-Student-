package com.example.cadastroaluno.open_api;

import com.example.cadastroaluno.dto.request.TipoUsuarioRequestDTO;
import com.example.cadastroaluno.dto.response.TipoUsuarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TipoUsuarioOpenAPI {

    @Operation(summary = "Buscar tipo de usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado")
    })
    @GetMapping("/buscarPorId/{id}")
    ResponseEntity<TipoUsuarioResponseDTO> buscarTipoUsuarioPorId(@PathVariable Integer id);


    @Operation(summary = "Listar todos os tipos de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/listar")
    List<TipoUsuarioResponseDTO> listarTipoUsuario();


    @Operation(summary = "Cadastrar novo tipo de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/cadastrar")
    ResponseEntity<String> inserirTipoUsuario(
            @RequestBody TipoUsuarioRequestDTO dto
    );


    @Operation(summary = "Atualizar tipo de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    ResponseEntity<String> atualizarTipoUsuario(
            @PathVariable Integer id,
            @RequestBody TipoUsuarioRequestDTO dto
    );


    @Operation(summary = "Excluir tipo de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado")
    })
    @DeleteMapping("/excluir/{id}")
    ResponseEntity<String> excluirTipoUsuario(@PathVariable Integer id);

}