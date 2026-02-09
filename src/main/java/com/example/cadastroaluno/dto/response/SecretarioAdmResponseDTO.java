package com.example.cadastroaluno.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class SecretarioAdmResponseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSecretario;

    private String nome;

    private Boolean ativo;

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

}
