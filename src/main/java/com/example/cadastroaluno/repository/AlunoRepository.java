package com.example.cadastroaluno.repository;

import com.example.cadastroaluno.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    //métodos derivados
    boolean existsByMatricula(String matricula);

}
