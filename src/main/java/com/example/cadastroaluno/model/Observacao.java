package com.example.cadastroaluno.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Observacao")
public class Observacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idObservacao;

    @Column(nullable = false)
    private String descricao;

    private LocalDate dataObs;

    @Column(name = "lida", nullable = false)
    private Boolean notificacaoLida = false;

    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_professor", nullable = false)
    private Professor professor;

}
