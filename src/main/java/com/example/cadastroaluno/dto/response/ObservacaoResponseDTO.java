package com.example.cadastroaluno.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
public class ObservacaoResponseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idObservacao;

    private String descricao;

    private Date dataObs;

    @Column(name = "aluno_id", nullable = false)
    private Integer alunoId;

    @Column(name = "professor_id", nullable = false)
    private Integer professorId;

}
