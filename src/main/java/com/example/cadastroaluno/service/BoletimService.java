package com.example.cadastroaluno.service;

import com.example.cadastroaluno.dto.request.BoletimRequestDTO;
import com.example.cadastroaluno.dto.response.BoletimResponseDTO;
import com.example.cadastroaluno.exception.AlunoNaoEncontradoException;
import com.example.cadastroaluno.exception.BoletimNaoEncontradoException;
import com.example.cadastroaluno.exception.DisciplinaNaoEncontradaException;
import com.example.cadastroaluno.model.Aluno;
import com.example.cadastroaluno.model.Boletim;
import com.example.cadastroaluno.model.Disciplina;
import com.example.cadastroaluno.repository.AlunoRepository;
import com.example.cadastroaluno.repository.BoletimRepository;
import com.example.cadastroaluno.repository.DisciplinaRepository;
import jakarta.persistence.Column;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BoletimService {
    private final BoletimRepository boletimRepository;
    private final DisciplinaRepository discplinaRepository;
    private final AlunoRepository alunoRepository;

    public BoletimService(BoletimRepository boletimRepository, DisciplinaRepository disciplinaRepository,
                          AlunoRepository alunoRepository) {
        this.boletimRepository = boletimRepository;
        this.discplinaRepository = disciplinaRepository;
        this.alunoRepository = alunoRepository;
    }

    private Boletim toEntity(BoletimRequestDTO dto) {
        Boletim boletim = new Boletim();

        boletim.setNota1(BigDecimal.valueOf(dto.getNota1()));
        boletim.setNota2(BigDecimal.valueOf(dto.getNota2()));


        Disciplina disciplina = discplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new DisciplinaNaoEncontradaException(dto.getDisciplinaId()));

        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new AlunoNaoEncontradoException(dto.getAlunoId()));

        boletim.setDisciplina(disciplina);
        boletim.setAluno(aluno);

        return boletim;
    }

    private BoletimResponseDTO toResponseDTO(Boletim boletim) {
        BoletimResponseDTO dto = new BoletimResponseDTO();

        dto.setIdBoletim(boletim.getIdBoletim());
        dto.setNota1(boletim.getNota1().doubleValue());
        dto.setNota2(boletim.getNota2().doubleValue());
        dto.setMedia(boletim.getMedia().doubleValue());
        dto.setDisciplinaId(boletim.getDisciplina().getIdDisciplina());
        dto.setAlunoId(boletim.getAluno().getIdAluno());

        return dto;
    }

    public BoletimResponseDTO buscarPorId(Integer id){
        Boletim Boletim= boletimRepository.findById(id)
                .orElseThrow(() -> new BoletimNaoEncontradoException(id));
        return toResponseDTO(Boletim);
    }
    public List<BoletimResponseDTO> listarBoletim() {
        return boletimRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public BoletimResponseDTO cadastrarBoletim(BoletimRequestDTO dto) {
        Boletim Boletim = toEntity(dto);
        Boletim = boletimRepository.save(Boletim);
        return toResponseDTO(Boletim);
    }

    public void excluirBoletim(Integer id) {
        Boletim Boletim = boletimRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Boletim com ID " + id + " não encontrado"));

        boletimRepository.delete(Boletim);
    }

    public BoletimResponseDTO atualizarBoletim(Integer id, BoletimRequestDTO dto) {
        Boletim boletim = boletimRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Boletim com ID " + id + " não encontrado"));

        if (dto.getNota1() != null) {
            boletim.setNota1(BigDecimal.valueOf(dto.getNota1()));
        }
        if (dto.getNota2() != null) {
            boletim.setNota2(BigDecimal.valueOf(dto.getNota2()));
        }
        if (dto.getDisciplinaId() != null) {
            Disciplina disciplina = discplinaRepository.findById(dto.getDisciplinaId())
                    .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
            boletim.setDisciplina(disciplina);

        }
        if (dto.getAlunoId() != null) {
            Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                    .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
            boletim.setAluno(aluno);
        }

        Boletim atualizado = boletimRepository.save(boletim);
        return toResponseDTO(atualizado);
    }

}
