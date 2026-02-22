package com.example.cadastroaluno.repository;

import com.example.cadastroaluno.model.Boletim;
import com.example.cadastroaluno.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoletimRepository extends JpaRepository<Boletim, Integer> {

    @Query("SELECT b.disciplina FROM Boletim b WHERE b.aluno.idAluno = :idAluno")
    List<Disciplina> findDisciplinasPorAluno(@Param("idAluno") Integer idAluno);

}
