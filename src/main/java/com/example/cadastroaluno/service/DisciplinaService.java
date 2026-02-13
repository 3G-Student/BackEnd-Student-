package com.example.cadastroaluno.service;

import com.example.cadastroaluno.dto.request.DisciplinaRequestDTO;
import com.example.cadastroaluno.dto.response.DisciplinaResponseDTO;
import com.example.cadastroaluno.exception.DisciplinaNaoEncontradaException;
import com.example.cadastroaluno.exception.MatriculaDuplicadaException;
import com.example.cadastroaluno.model.Disciplina;
import com.example.cadastroaluno.repository.DisciplinaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    private Disciplina toEntity(DisciplinaRequestDTO dto) {
        Disciplina disciplina = new Disciplina();

        disciplina.setNome(dto.getNome());

        return disciplina;
    }

    private DisciplinaResponseDTO toResponseDTO(Disciplina disciplina) {
        DisciplinaResponseDTO dto = new DisciplinaResponseDTO();

        dto.setIdDisciplina(disciplina.getIdDisciplina());
        dto.setNome(disciplina.getNome());

        return dto;
    }

    public DisciplinaResponseDTO buscarPorId(Integer id){
        Disciplina disciplina= disciplinaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada"));
        return toResponseDTO(disciplina);
    }
    public List<DisciplinaResponseDTO> listarDisciplina() {
        return disciplinaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public DisciplinaResponseDTO cadastrarDisciplina(DisciplinaRequestDTO dto) {
//        if (disciplinaRepository.existsByNome(dto.getNome())) {
//            throw new NomeDuplicadoException(dto.getNome());
//        }

        Disciplina disciplina = toEntity(dto);
        disciplina = disciplinaRepository.save(disciplina);
        return toResponseDTO(disciplina);
    }

    public void excluirDisciplina(Integer id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new DisciplinaNaoEncontradaException(id));

        disciplinaRepository.delete(disciplina);
    }

    public DisciplinaResponseDTO atualizarDisciplina(Integer id, DisciplinaRequestDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Disciplina com ID " + id + " não encontrado"));

        if (dto.getNome() != null) {
            disciplina.setNome(dto.getNome());
        }

        Disciplina atualizado = disciplinaRepository.save(disciplina);
        return toResponseDTO(atualizado);
    }

}
