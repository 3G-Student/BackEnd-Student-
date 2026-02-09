package com.example.cadastroaluno.controller;

import com.example.cadastroaluno.dto.request.UsuarioRequestDTO;
import com.example.cadastroaluno.dto.response.UsuarioResponseDTO;
import com.example.cadastroaluno.service.UsuarioService;
import com.example.cadastroaluno.validation.OnCreate;
import com.example.cadastroaluno.validation.OnPatch;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService service) {
        this.usuarioService = service;
    }

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
    public ResponseEntity<String> inserirUsuario(@Validated({OnCreate.class, Default.class})
                                                    @RequestBody UsuarioRequestDTO dto) {
        usuarioService.cadastrarUsuario(dto);
        return ResponseEntity.ok("Usuario inserido com sucesso!");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable Integer id,
                                                      @Validated({OnPatch.class, Default.class})
                                                      @RequestBody UsuarioRequestDTO dto) {
        usuarioService.atualizarUsuario(id, dto);
        return ResponseEntity.ok("Usuario atualizado com sucesso!");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable Integer id) {
        usuarioService.excluirUsuario(id);
        return ResponseEntity.ok("Usuario excluído com sucesso!");
    }

}
