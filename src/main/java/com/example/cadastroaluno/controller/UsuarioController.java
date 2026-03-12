package com.example.cadastroaluno.controller;

import com.example.cadastroaluno.dto.request.SenhaRequestDTO;
import com.example.cadastroaluno.dto.request.UsuarioLoginDTO;
import com.example.cadastroaluno.dto.request.UsuarioRequestDTO;
import com.example.cadastroaluno.dto.request.UsuarioUpdateRequestDTO;
import com.example.cadastroaluno.dto.response.LoginResponseDTO;
import com.example.cadastroaluno.dto.response.PerfilUsuarioResponseDTO;
import com.example.cadastroaluno.dto.response.UsuarioResponseDTO;
import com.example.cadastroaluno.open_api.UsuarioOpenAPI;
import com.example.cadastroaluno.service.UsuarioService;
import com.example.cadastroaluno.validation.OnCreate;
import com.example.cadastroaluno.validation.OnPatch;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Usuario")
@AllArgsConstructor
public class UsuarioController implements UsuarioOpenAPI {

    private final UsuarioService usuarioService;

    //    Métodos comuns
    @GetMapping("buscarPorId/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Integer id) {
        UsuarioResponseDTO usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/listar")
    public List<UsuarioResponseDTO> listarUsuario(){
        return usuarioService.listarUsuario();
    }

    @PostMapping("/cadastrar")
    public UsuarioResponseDTO inserirUsuario(@Validated({OnCreate.class, Default.class})
                                                    @RequestBody UsuarioRequestDTO dto) {
        return usuarioService.cadastrarUsuario(dto);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable Integer id,
                                                      @Validated({OnPatch.class})
                                                      @RequestBody UsuarioUpdateRequestDTO dto) {
        usuarioService.atualizarUsuario(id, dto);
        return ResponseEntity.ok("Usuario atualizado com sucesso!");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable Integer id) {
        usuarioService.excluirUsuario(id);
        return ResponseEntity.ok("Usuario excluído com sucesso!");
    }

    @PatchMapping("/atualizarSenha/{id}")
    public ResponseEntity<String> atualizarSenha(
            @PathVariable Integer id,
            @Valid @RequestBody SenhaRequestDTO request) {

        usuarioService.atualizarSenha(id, request.getNovaSenha());

        return ResponseEntity.ok("Senha atualizada com sucesso!");
    }

    //Querys

    @GetMapping("/perfil/{id}")
    public ResponseEntity<?> buscarPerfil(@PathVariable Integer id) {
        PerfilUsuarioResponseDTO perfil = usuarioService.buscarPerfilUsuario(id);
        if (perfil == null) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }
        return ResponseEntity.ok(perfil);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDTO loginDTO) {

        try {

            LoginResponseDTO usuario = usuarioService.validarLogin(loginDTO);

            if (usuario == null) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Email ou senha inválidos");
            }

            return ResponseEntity.ok(usuario);

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Email ou senha inválidos");
        }
    }
}
