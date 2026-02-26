package com.example.cadastroaluno.dto.response;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ObservacaoResponseDTO {

    private Integer idObservacao;

    private String descricao;

    private LocalDate dataObs;

    private Boolean notificacaoLida;

    @Column(name = "id_aluno", nullable = false)
    private Integer alunoId;

    @Column(name = "id_professor", nullable = false)
    private Integer professorId;

}
