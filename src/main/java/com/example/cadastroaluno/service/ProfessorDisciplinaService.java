package com.example.cadastroaluno.service;

import com.example.cadastroaluno.dto.response.ProfessorDisciplinaResponseDTO;
import com.example.cadastroaluno.model.ProfessorDisciplina;
import com.example.cadastroaluno.model.ProfessorDisciplinaId;
import com.example.cadastroaluno.repository.ProfessorDisciplinaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorDisciplinaService {

    private final ProfessorDisciplinaRepository professorDisciplinaRepository;

    public ProfessorDisciplinaService(ProfessorDisciplinaRepository professorDisciplinaRepository) {
        this.professorDisciplinaRepository = professorDisciplinaRepository;
    }

    private ProfessorDisciplinaResponseDTO toResponseDTO(ProfessorDisciplina entity) {
        ProfessorDisciplinaResponseDTO dto = new ProfessorDisciplinaResponseDTO();

        dto.setDisciplinaId(entity.getDisciplina().getIdDisciplina());
        dto.setProfessorId(entity.getProfessor().getIdProfessor());

        return dto;
    }

    public List<ProfessorDisciplinaResponseDTO> listarTodos() {
        return professorDisciplinaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ProfessorDisciplinaResponseDTO buscarPorId(Integer disciplinaId, Integer professorId) {

        ProfessorDisciplinaId id = new ProfessorDisciplinaId();
        id.setDisciplina(disciplinaId);
        id.setProfessor(professorId);

        ProfessorDisciplina entity = professorDisciplinaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Relação professor com disciplina não encontrada"));

        return toResponseDTO(entity);
    }

}
