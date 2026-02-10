package com.example.cadastroaluno.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "boletim")
public class Boletim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boletim")
    private Integer idBoletim;

    @Column(name = "n1", nullable = false)
    private BigDecimal nota1;

    @Column(name = "n2", nullable = false)
    private BigDecimal nota2;

    @Column(name = "media", insertable = false, updatable = false)
    private BigDecimal media;

    @ManyToOne
    @JoinColumn(name = "id_disciplina", nullable = false)
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false)
    private Aluno aluno;
}
