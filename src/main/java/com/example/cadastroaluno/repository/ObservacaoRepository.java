package com.example.cadastroaluno.repository;

import com.example.cadastroaluno.model.Observacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservacaoRepository extends JpaRepository<Observacao, Integer> {

    List<Observacao> findByAluno_IdAluno(Integer idAluno);

    List<Observacao> findByProfessor_IdProfessor(Integer idProfessor);
}
