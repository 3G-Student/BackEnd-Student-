package com.example.cadastroaluno.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "secretario_adm")
public class SecretarioAdm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSecretario;

    private String nome;

    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
