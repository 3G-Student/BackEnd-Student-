package com.example.cadastroaluno.dto.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AlunoResponseDTO {

    private Integer idAluno;

    private String nome;

    private String matricula;

    private Boolean ativo;

    private Integer usuarioId;
}
