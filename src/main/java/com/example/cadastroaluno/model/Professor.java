package com.example.cadastroaluno.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProfessor;

    private String nome;

    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


}
