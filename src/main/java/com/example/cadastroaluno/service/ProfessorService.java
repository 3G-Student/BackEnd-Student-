package com.example.cadastroaluno.service;

import com.example.cadastroaluno.dto.request.ProfessorRequestDTO;
import com.example.cadastroaluno.dto.response.ProfessorResponseDTO;
import com.example.cadastroaluno.exception.AlunoNaoEncontradoException;
import com.example.cadastroaluno.exception.DisciplinaNaoEncontradaException;
import com.example.cadastroaluno.exception.UsuarioNaoEncontradoException;
import com.example.cadastroaluno.model.Aluno;
import com.example.cadastroaluno.model.Professor;
import com.example.cadastroaluno.model.Disciplina;
import com.example.cadastroaluno.model.Usuario;
import com.example.cadastroaluno.repository.AlunoRepository;
import com.example.cadastroaluno.repository.ProfessorRepository;
import com.example.cadastroaluno.repository.DisciplinaRepository;
import com.example.cadastroaluno.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {


    private final ProfessorRepository ProfessorRepository;
    private final UsuarioRepository usuarioRepository;

    public ProfessorService(ProfessorRepository ProfessorRepository, UsuarioRepository usuarioRepository) {
        this.ProfessorRepository = ProfessorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    private Professor toEntity(ProfessorRequestDTO dto) {
        Professor professor = new Professor();

        professor.setNome(dto.getNome());
        professor.setAtivo(dto.getAtivo());

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(dto.getUsuarioId()));

        professor.setUsuario(usuario);

        return professor;
    }

    private ProfessorResponseDTO toResponseDTO(Professor professor) {
        ProfessorResponseDTO dto = new ProfessorResponseDTO();

        dto.setNome(professor.getNome());
        dto.setAtivo(professor.isAtivo());
        dto.setUsuarioId(professor.getUsuario().getIdUsuario());

        return dto;
    }

    public ProfessorResponseDTO buscarPorId(Integer id){
        Professor Professor= ProfessorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));
        return toResponseDTO(Professor);
    }
    public List<ProfessorResponseDTO> listarProfessor() {
        return ProfessorRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ProfessorResponseDTO cadastrarProfessor(ProfessorRequestDTO dto) {
        Professor Professor = toEntity(dto);
        Professor = ProfessorRepository.save(Professor);
        return toResponseDTO(Professor);
    }

    public void excluirProfessor(Integer id) {
        Professor Professor = ProfessorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor com ID " + id + " não encontrado"));

        ProfessorRepository.delete(Professor);
    }

    public ProfessorResponseDTO atualizarProfessor(Integer id, ProfessorRequestDTO dto) {
        Professor professor = ProfessorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor com ID " + id + " não encontrado"));

        if (dto.getNome() != null) {
            professor.setNome(dto.getNome());
        }
        if (dto.getAtivo() != null) {
            professor.setAtivo(dto.getAtivo());
        }
        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            professor.setUsuario(usuario);

        }

        Professor atualizado = ProfessorRepository.save(professor);
        return toResponseDTO(atualizado);
    }

}
