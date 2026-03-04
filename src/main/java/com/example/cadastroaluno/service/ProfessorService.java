package com.example.cadastroaluno.service;

import com.example.cadastroaluno.dto.request.ProfessorRequestDTO;
import com.example.cadastroaluno.dto.response.AlunoRecuperacaoResponseDTO;
import com.example.cadastroaluno.dto.response.DisciplinaResponseDTO;
import com.example.cadastroaluno.dto.response.ProfessorResponseDTO;
import com.example.cadastroaluno.exception.*;
import com.example.cadastroaluno.model.Aluno;
import com.example.cadastroaluno.model.Professor;
import com.example.cadastroaluno.model.Disciplina;
import com.example.cadastroaluno.model.Usuario;
import com.example.cadastroaluno.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final UsuarioRepository usuarioRepository;
    private final BoletimRepository boletimRepository;

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

        dto.setIdProfessor(professor.getIdProfessor());
        dto.setNome(professor.getNome());
        dto.setAtivo(professor.isAtivo());
        dto.setUsuarioId(professor.getUsuario().getIdUsuario());

        return dto;
    }

    public ProfessorResponseDTO buscarPorId(Integer id){
        Professor Professor= professorRepository.findById(id)
                .orElseThrow(() -> new ProfessorNaoEncontradoException(id));
        return toResponseDTO(Professor);
    }
    public List<ProfessorResponseDTO> listarProfessor() {
        return professorRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ProfessorResponseDTO cadastrarProfessor(ProfessorRequestDTO dto) {

        if (professorRepository.existsByUsuario_IdUsuario(dto.getUsuarioId())) {
            throw new UsuarioJaPossuiProfessorException();
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(dto.getUsuarioId()));

        if (usuario.getTipoUsuario().getIdTipo() != 2) {
            throw new TipoUsuarioInvalidoException();
        }

        Professor professor = toEntity(dto);
        professor.setUsuario(usuario);
        professor = professorRepository.save(professor);

        return toResponseDTO(professor);
    }


    public void excluirProfessor(Integer id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor com ID " + id + " não encontrado"));

        professorRepository.delete(professor);
    }

    public ProfessorResponseDTO atualizarProfessor(Integer id, ProfessorRequestDTO dto) {
        Professor professor = professorRepository.findById(id)
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

        Professor atualizado = professorRepository.save(professor);
        return toResponseDTO(atualizado);
    }

    //Query

    public List<AlunoRecuperacaoResponseDTO> listarAlunosDeRecuperacao(Integer idProfessor) {
        return boletimRepository.buscarAlunosDeRecuperacao(idProfessor);
    }

}
