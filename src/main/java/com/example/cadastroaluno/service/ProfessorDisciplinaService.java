package com.example.cadastroaluno.service;

import com.example.cadastroaluno.dto.request.ProfessorDisciplinaRequestDTO;
import com.example.cadastroaluno.dto.response.ProfessorDisciplinaResponseDTO;
import com.example.cadastroaluno.model.Disciplina;
import com.example.cadastroaluno.model.Professor;
import com.example.cadastroaluno.model.ProfessorDisciplina;
import com.example.cadastroaluno.model.ProfessorDisciplinaId;
import com.example.cadastroaluno.repository.DisciplinaRepository;
import com.example.cadastroaluno.repository.ProfessorDisciplinaRepository;
import com.example.cadastroaluno.repository.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorDisciplinaService {

    private final ProfessorDisciplinaRepository professorDisciplinaRepository;
    private final ProfessorRepository professorRepository;
    private final DisciplinaRepository disciplinaRepository;

    private ProfessorDisciplina toEntity(ProfessorDisciplinaRequestDTO dto) {

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada"));

        ProfessorDisciplina pd = new ProfessorDisciplina();
        pd.setProfessor(professor);
        pd.setDisciplina(disciplina);

        return pd;
    }

    private ProfessorDisciplinaResponseDTO toResponseDTO(ProfessorDisciplina entity) {

        ProfessorDisciplinaResponseDTO dto = new ProfessorDisciplinaResponseDTO();

        dto.setProfessorId(entity.getProfessor().getIdProfessor());
        dto.setDisciplinaId(entity.getDisciplina().getIdDisciplina());

        return dto;
    }

    public ProfessorDisciplinaResponseDTO vincular(ProfessorDisciplinaRequestDTO dto) {

        if (professorDisciplinaRepository.existsByProfessor_IdProfessorAndDisciplina_IdDisciplina(
                dto.getProfessorId(), dto.getDisciplinaId())) {

            throw new RuntimeException("Esse professor já está vinculado a essa disciplina");
        }

        ProfessorDisciplina pd = toEntity(dto);
        pd = professorDisciplinaRepository.save(pd);

        return toResponseDTO(pd);
    }

    public List<ProfessorDisciplinaResponseDTO> listarTodos() {
        return professorDisciplinaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public void remover(Integer professorId, Integer disciplinaId) {

        ProfessorDisciplinaId id = new ProfessorDisciplinaId(disciplinaId, professorId);

        if (!professorDisciplinaRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Vínculo entre professor " + professorId +
                            " e disciplina " + disciplinaId + " não encontrado"
            );
        }

        professorDisciplinaRepository.deleteById(id);
    }
}