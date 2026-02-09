package com.example.cadastroaluno.repository;

import com.example.cadastroaluno.model.ProfessorDisciplina;
import com.example.cadastroaluno.model.ProfessorDisciplinaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorDisciplinaRepository extends JpaRepository<ProfessorDisciplina, ProfessorDisciplinaId> {
}
