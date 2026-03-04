package com.example.cadastroaluno.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoRecuperacaoResponseDTO {

    private Integer idAluno;
    private String nomeAluno;
    private String disciplina;
    private BigDecimal media;

}