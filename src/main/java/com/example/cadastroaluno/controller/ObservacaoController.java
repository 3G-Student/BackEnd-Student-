package com.example.cadastroaluno.controller;

import com.example.cadastroaluno.dto.request.ObservacaoRequestDTO;
import com.example.cadastroaluno.dto.request.ObservacaoUpdateRequestDTO;
import com.example.cadastroaluno.dto.response.ObservacaoResponseDTO;
import com.example.cadastroaluno.service.ObservacaoService;
import com.example.cadastroaluno.validation.OnCreate;
import com.example.cadastroaluno.validation.OnPatch;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Observacao")
public class ObservacaoController {
    private final ObservacaoService observacaoService;

    public ObservacaoController(ObservacaoService service) {
        this.observacaoService = service;
    }

    //    Métodos comuns
    @GetMapping("buscarPorId/{id}")
    public ResponseEntity<ObservacaoResponseDTO> buscarObservacaoPorId(@PathVariable Integer id) {
        ObservacaoResponseDTO observacao = observacaoService.buscarPorId(id);
        return ResponseEntity.ok(observacao);
    }

    @GetMapping("/listar")
    public List<ObservacaoResponseDTO> listarObservacao(){
        return observacaoService.listarObservacao();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> inserirObservacao(@Validated({OnCreate.class})
                                                    @RequestBody ObservacaoRequestDTO dto) {
        observacaoService.cadastrarObservacao(dto);
        return ResponseEntity.ok("Observacao inserido com sucesso!");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarObservacao(@PathVariable Integer id,
                                                      @Validated({OnPatch.class})
                                                      @RequestBody ObservacaoUpdateRequestDTO dto) {
        observacaoService.atualizarObservacao(id, dto);
        return ResponseEntity.ok("Observacao atualizado com sucesso!");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirObservacao(@PathVariable Integer id) {
        observacaoService.excluirObservacao(id);
        return ResponseEntity.ok("Observacao excluído com sucesso!");
    }
}
