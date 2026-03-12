package com.example.cadastroaluno.open_api;

import com.example.cadastroaluno.dto.request.*;
import com.example.cadastroaluno.dto.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UsuarioOpenAPI {

    @Operation(summary = "Buscar usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/buscarPorId/{id}")
    ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Integer id);


    @Operation(summary = "Listar todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/listar")
    List<UsuarioResponseDTO> listarUsuario();


    @Operation(summary = "Cadastrar novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/cadastrar")
    UsuarioResponseDTO inserirUsuario(@RequestBody UsuarioRequestDTO dto);


    @Operation(summary = "Atualizar usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    ResponseEntity<String> atualizarUsuario(
            @PathVariable Integer id,
            @RequestBody UsuarioUpdateRequestDTO dto
    );


    @Operation(summary = "Excluir usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/excluir/{id}")
    ResponseEntity<String> excluirUsuario(@PathVariable Integer id);


    @Operation(summary = "Atualizar senha do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PatchMapping("/atualizarSenha/{id}")
    ResponseEntity<String> atualizarSenha(
            @PathVariable Integer id,
            @RequestBody SenhaRequestDTO request
    );


    @Operation(summary = "Buscar perfil do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/perfil/{id}")
    ResponseEntity<?> buscarPerfil(@PathVariable Integer id);


    @Operation(summary = "Login do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Email ou senha inválidos")
    })
    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody UsuarioLoginDTO loginDTO);

}