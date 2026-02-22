package com.example.cadastroaluno.service;

import com.example.cadastroaluno.dto.response.ProfessorDisciplinaResponseDTO;
import com.example.cadastroaluno.model.ProfessorDisciplina;
import com.example.cadastroaluno.model.ProfessorDisciplinaId;
import com.example.cadastroaluno.repository.ProfessorDisciplinaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorDisciplinaService {

    private final ProfessorDisciplinaRepository professorDisciplinaRepository;

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

}
