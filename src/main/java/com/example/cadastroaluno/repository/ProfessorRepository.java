package com.example.cadastroaluno.repository;

import com.example.cadastroaluno.model.Professor;
import com.example.cadastroaluno.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    boolean existsByUsuario_IdUsuario(Integer usuarioId);

    Optional<Professor> findByUsuario(Usuario usuario);
}
