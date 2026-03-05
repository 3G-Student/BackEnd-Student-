package com.example.cadastroaluno.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginResponseDTO {

    private Integer idUsuario;
    private Integer idTipoUsuario;
    private Integer idProfessor;
    private Integer idAluno;
    private Integer idSecretario;
    private String token;

}
