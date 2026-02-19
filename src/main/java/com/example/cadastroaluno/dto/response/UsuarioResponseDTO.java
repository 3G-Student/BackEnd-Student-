package com.example.cadastroaluno.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private Integer idUsuario;

    private String email;

    @Column(name = "id_tipo", nullable = false)
    private Integer tipoId;

}
