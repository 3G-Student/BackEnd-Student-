package com.example.cadastroaluno.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ObservacaoResponseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idObservacao;

    private String descricao;

    private LocalDate dataObs;

    @Column(name = "id_aluno", nullable = false)
    private Integer alunoId;

    @Column(name = "id_professor", nullable = false)
    private Integer professorId;

}
