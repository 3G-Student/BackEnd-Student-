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
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;
}