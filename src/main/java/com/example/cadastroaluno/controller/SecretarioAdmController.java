package com.example.cadastroaluno.controller;

import com.example.cadastroaluno.dto.request.SecretarioAdmRequestDTO;
import com.example.cadastroaluno.dto.response.SecretarioAdmResponseDTO;
import com.example.cadastroaluno.service.SecretarioAdmService;
import com.example.cadastroaluno.validation.OnCreate;
import com.example.cadastroaluno.validation.OnPatch;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/SecretarioAdm")
@AllArgsConstructor
public class SecretarioAdmController {

    private final SecretarioAdmService secretarioAdmService;

    //    Métodos comuns
    @GetMapping("buscarPorId/{id}")
    public ResponseEntity<SecretarioAdmResponseDTO> buscarSecretarioAdmPorId(@PathVariable Integer id) {
        SecretarioAdmResponseDTO secretarioAdm = secretarioAdmService.buscarPorId(id);
        return ResponseEntity.ok(secretarioAdm);
    }

    @GetMapping("/listar")
    public List<SecretarioAdmResponseDTO> listarSecretarioAdm(){
        return secretarioAdmService.listarSecretarioAdm();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> inserirSecretarioAdm(@Validated({OnCreate.class, Default.class})
                                                    @RequestBody SecretarioAdmRequestDTO dto) {
        secretarioAdmService.cadastrarSecretarioAdm(dto);
        return ResponseEntity.ok("SecretarioAdm inserido com sucesso!");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarSecretarioAdm(@PathVariable Integer id,
                                                      @Validated({OnPatch.class, Default.class})
                                                      @RequestBody SecretarioAdmRequestDTO dto) {
        secretarioAdmService.atualizarSecretarioAdm(id, dto);
        return ResponseEntity.ok("SecretarioAdm atualizado com sucesso!");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirSecretarioAdm(@PathVariable Integer id) {
        secretarioAdmService.excluirSecretarioAdm(id);
        return ResponseEntity.ok("SecretarioAdm excluído com sucesso!");
    }

}
