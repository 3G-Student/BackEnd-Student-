package com.example.cadastroaluno.controller;

import com.example.cadastroaluno.dto.request.BoletimRequestDTO;
import com.example.cadastroaluno.dto.response.BoletimResponseDTO;
import com.example.cadastroaluno.service.BoletimService;
import com.example.cadastroaluno.validation.OnCreate;
import com.example.cadastroaluno.validation.OnPatch;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boletim")
@AllArgsConstructor
public class BoletimController {

    private final BoletimService boletimService;

    //    Métodos comuns
    @GetMapping("buscarPorId/{id}")
    public ResponseEntity<BoletimResponseDTO> buscarBoletimPorId(@PathVariable Integer id) {
        BoletimResponseDTO boletim = boletimService.buscarPorId(id);
        return ResponseEntity.ok(boletim);
    }

    @GetMapping("/listar")
    public List<BoletimResponseDTO> listarBoletim(){
        return boletimService.listarBoletim();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> inserirBoletim(@Validated({OnCreate.class, Default.class})
                                               @RequestBody BoletimRequestDTO dto) {
        boletimService.cadastrarBoletim(dto);
        return ResponseEntity.ok("Boletim inserido com sucesso!");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarBoletim(@PathVariable Integer id,
                                                    @Validated({OnPatch.class, Default.class})
                                                    @RequestBody BoletimRequestDTO dto) {
        boletimService.atualizarBoletim(id, dto);
        return ResponseEntity.ok("Boletim atualizado com sucesso!");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirBoletim(@PathVariable Integer id) {
        boletimService.excluirBoletim(id);
        return ResponseEntity.ok("Boletim excluído com sucesso!");
    }

}
