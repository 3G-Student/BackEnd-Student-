package com.example.cadastroaluno.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "professor_disciplina")
@IdClass(ProfessorDisciplinaId.class)
public class ProfessorDisciplina {

    @Id
    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    @Id
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
}