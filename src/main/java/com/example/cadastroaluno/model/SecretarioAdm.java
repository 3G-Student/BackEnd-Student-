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

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "usuario_id",  nullable = false)
    private Usuario usuario;

}
