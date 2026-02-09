package com.example.cadastroaluno.controller;

import com.example.cadastroaluno.dto.request.TipoUsuarioRequestDTO;
import com.example.cadastroaluno.dto.response.TipoUsuarioResponseDTO;
import com.example.cadastroaluno.service.TipoUsuarioService;
import com.example.cadastroaluno.validation.OnCreate;
import com.example.cadastroaluno.validation.OnPatch;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/TipoUsuario")
public class TipoUsuarioController {
    private final TipoUsuarioService tipoUsuarioService;

    public TipoUsuarioController(TipoUsuarioService service) {
        this.tipoUsuarioService = service;
    }

    //    Métodos comuns
    @GetMapping("buscarPorId/{id}")
    public ResponseEntity<TipoUsuarioResponseDTO> buscarTipoUsuarioPorId(@PathVariable Integer id) {
        TipoUsuarioResponseDTO tipoUsuario = tipoUsuarioService.buscarPorId(id);
        return ResponseEntity.ok(tipoUsuario);
    }

    @GetMapping("/listar")
    public List<TipoUsuarioResponseDTO> listarTipoUsuario(){
        return tipoUsuarioService.listarTipoUsuario();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> inserirTipoUsuario(@Validated({OnCreate.class, Default.class})
                                                    @RequestBody TipoUsuarioRequestDTO dto) {
        tipoUsuarioService.cadastrarTipoUsuario(dto);
        return ResponseEntity.ok("TipoUsuario inserido com sucesso!");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarTipoUsuario(@PathVariable Integer id,
                                                      @Validated({OnPatch.class, Default.class})
                                                      @RequestBody TipoUsuarioRequestDTO dto) {
        tipoUsuarioService.atualizarTipoUsuario(id, dto);
        return ResponseEntity.ok("TipoUsuario atualizado com sucesso!");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirTipoUsuario(@PathVariable Integer id) {
        tipoUsuarioService.excluirTipoUsuario(id);
        return ResponseEntity.ok("TipoUsuario excluído com sucesso!");
    }

}
