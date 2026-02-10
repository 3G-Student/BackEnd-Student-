package com.example.cadastroaluno.repository;

import com.example.cadastroaluno.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
    boolean existsByNome(String nome);
}
