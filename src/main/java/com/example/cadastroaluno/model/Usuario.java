package com.example.cadastroaluno.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    private String email;

    private String senha;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private TipoUsuario tipoUsuario;


}
