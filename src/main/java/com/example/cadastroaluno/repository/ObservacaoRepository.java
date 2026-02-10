package com.example.cadastroaluno.repository;

import com.example.cadastroaluno.model.Observacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservacaoRepository extends JpaRepository<Observacao, Integer> {
}
