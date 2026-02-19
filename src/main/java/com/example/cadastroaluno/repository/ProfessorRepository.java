package com.example.cadastroaluno.repository;

import com.example.cadastroaluno.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    boolean existsByUsuario_IdUsuario(Integer usuarioId);
}
