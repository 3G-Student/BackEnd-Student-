package com.example.cadastroaluno.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ObservacaoUpdateResponseDTO {

    private Integer idObservacao;

    private String descricao;

    private LocalDate dataObs;

}
