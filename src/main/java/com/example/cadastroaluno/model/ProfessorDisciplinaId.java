package com.example.cadastroaluno.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDisciplinaId implements Serializable {
    private Integer disciplina;
    private Integer professor;
}
