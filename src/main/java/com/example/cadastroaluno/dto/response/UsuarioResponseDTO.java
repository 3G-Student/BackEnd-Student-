package com.example.cadastroaluno.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UsuarioResponseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    private String email;

    private String senha;

    @Column(name = "tipo_id", nullable = false)
    private Integer tipoId;

}
