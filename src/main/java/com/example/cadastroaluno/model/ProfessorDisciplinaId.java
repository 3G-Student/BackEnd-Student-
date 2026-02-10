package com.example.cadastroaluno.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProfessorDisciplinaId implements Serializable {
    private Integer disciplina;
    private Integer professor;
}
