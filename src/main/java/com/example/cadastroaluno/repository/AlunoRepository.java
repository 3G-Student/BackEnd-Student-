package com.example.cadastroaluno.repository;

import com.example.cadastroaluno.model.Aluno;
import com.example.cadastroaluno.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    //métodos derivados
    boolean existsByMatricula(String matricula);

    boolean existsByUsuario_IdUsuario(Integer usuarioId);

    List<Aluno> findByAtivo(Boolean ativo);

    Optional<Aluno> findByUsuario(Usuario usuario);
}
