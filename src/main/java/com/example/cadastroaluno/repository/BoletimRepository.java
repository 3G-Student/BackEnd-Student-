package com.example.cadastroaluno.repository;

import com.example.cadastroaluno.dto.response.AlunoRecuperacaoResponseDTO;
import com.example.cadastroaluno.model.Boletim;
import com.example.cadastroaluno.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoletimRepository extends JpaRepository<Boletim, Integer> {

    @Query("SELECT b.disciplina FROM Boletim b WHERE b.aluno.idAluno = :idAluno")
    List<Disciplina> findDisciplinasPorAluno(@Param("idAluno") Integer idAluno);

    @Query("""
        SELECT new com.example.cadastroaluno.dto.response.AlunoRecuperacaoResponseDTO(
            a.idAluno,
            a.nome,
            d.nome,
            b.media
        )
        FROM Boletim b
        JOIN b.aluno a
        JOIN b.disciplina d
        JOIN ProfessorDisciplina pd ON pd.disciplina = d
        JOIN pd.professor p
        WHERE p.idProfessor = :professorId
        AND b.media < 7
    """)
    List<AlunoRecuperacaoResponseDTO> buscarAlunosDeRecuperacao(@Param("professorId") Integer professorId);

}
