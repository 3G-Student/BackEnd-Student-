package com.example.cadastroaluno.service;

import com.example.cadastroaluno.dto.request.ObservacaoRequestDTO;
import com.example.cadastroaluno.dto.request.ObservacaoUpdateRequestDTO;
import com.example.cadastroaluno.dto.response.ObservacaoResponseDTO;
import com.example.cadastroaluno.dto.response.ObservacaoUpdateResponseDTO;
import com.example.cadastroaluno.exception.AlunoNaoEncontradoException;
import com.example.cadastroaluno.exception.ObservacaoNaoEncontradaException;
import com.example.cadastroaluno.exception.ProfessorNaoEncontradoException;
import com.example.cadastroaluno.model.Aluno;
import com.example.cadastroaluno.model.Observacao;
import com.example.cadastroaluno.model.Professor;
import com.example.cadastroaluno.repository.AlunoRepository;
import com.example.cadastroaluno.repository.ObservacaoRepository;
import com.example.cadastroaluno.repository.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ObservacaoService {

    private final ObservacaoRepository observacaoRepository;
    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;

    private Observacao toEntity(ObservacaoRequestDTO dto) {
        Observacao observacao = new Observacao();

        observacao.setDescricao(dto.getDescricao());
        observacao.setDataObs(dto.getDataObs());

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new ProfessorNaoEncontradoException(dto.getProfessorId()));

        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new AlunoNaoEncontradoException(dto.getAlunoId()));

        observacao.setProfessor(professor);
        observacao.setAluno(aluno);

        return observacao;
    }

    private ObservacaoResponseDTO toResponseDTO(Observacao observacao) {
        ObservacaoResponseDTO dto = new ObservacaoResponseDTO();

        dto.setIdObservacao(observacao.getIdObservacao());
        dto.setDescricao(observacao.getDescricao());
        dto.setDataObs(observacao.getDataObs());
        dto.setNotificacaoLida(observacao.getNotificacaoLida());
        dto.setProfessorId(observacao.getProfessor().getIdProfessor());
        dto.setAlunoId(observacao.getAluno().getIdAluno());

        return dto;
    }

    private ObservacaoUpdateResponseDTO toUpdateResponseDTO(Observacao observacao) {
        ObservacaoUpdateResponseDTO dto = new ObservacaoUpdateResponseDTO();

        dto.setIdObservacao(observacao.getIdObservacao());
        dto.setDescricao(observacao.getDescricao());
        dto.setDataObs(observacao.getDataObs());

        return dto;
    }

    public ObservacaoResponseDTO buscarPorId(Integer id){
        Observacao observacao= observacaoRepository.findById(id)
                .orElseThrow(() -> new ObservacaoNaoEncontradaException(id));
        return toResponseDTO(observacao);
    }
    public List<ObservacaoResponseDTO> listarObservacao() {
        return observacaoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ObservacaoResponseDTO cadastrarObservacao(ObservacaoRequestDTO dto) {
        Observacao observacao = toEntity(dto);
        return toResponseDTO(observacaoRepository.save(observacao));
    }

    public void excluirObservacao(Integer id) {
        Observacao observacao = observacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Observacao com ID " + id + " não encontrado"));

        observacaoRepository.delete(observacao);
    }

    public ObservacaoUpdateResponseDTO atualizarObservacao(Integer id, ObservacaoUpdateRequestDTO dto) {
        Observacao observacao = observacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Observacao com ID " + id + " não encontrado"));

        if (dto.getDescricao() != null) {
            observacao.setDescricao(dto.getDescricao());
        }
        if (dto.getDataObs() != null) {
            observacao.setDataObs(dto.getDataObs());
        }
        if (dto.getNotificacaoLida() != null) {
            observacao.setNotificacaoLida(dto.getNotificacaoLida());
        }

        Observacao atualizado = observacaoRepository.save(observacao);
        return toUpdateResponseDTO(atualizado);
    }

    //Métodos derivados

    public List<ObservacaoResponseDTO> listarObsPorIdAluno(Integer alunoId){
        return observacaoRepository.findByAluno_IdAluno(alunoId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<ObservacaoResponseDTO> listarObsPorIdProfessor(Integer ProfessorId){
        return observacaoRepository.findByProfessor_IdProfessor(ProfessorId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }
    
}
