package com.example.cadastroaluno.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class BoletimResponseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBoletim;

    private Integer disciplinaId;

    private Integer alunoId;

    @Column(name = "n1")
    private Double nota1;

    @Column(name = "n2")
    private Double nota2;

    private Double media;

}
