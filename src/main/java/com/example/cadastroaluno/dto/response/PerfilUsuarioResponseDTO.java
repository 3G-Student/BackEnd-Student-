package com.example.cadastroaluno.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PerfilUsuarioResponseDTO {

    private String nome;
    private String email;
    private String senha;

}
