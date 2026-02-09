package com.example.cadastroaluno.service;

import com.example.cadastroaluno.dto.request.AlunoRequestDTO;
import com.example.cadastroaluno.dto.response.AlunoResponseDTO;
import com.example.cadastroaluno.exception.MatriculaDuplicadoException;
import com.example.cadastroaluno.exception.UsuarioNaoEncontradoException;
import com.example.cadastroaluno.model.Aluno;
import com.example.cadastroaluno.model.Usuario;
import com.example.cadastroaluno.repository.AlunoRepository;
import com.example.cadastroaluno.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final UsuarioRepository usuarioRepository;

    public AlunoService(AlunoRepository alunoRepository, UsuarioRepository usuarioRepository) {
        this.alunoRepository = alunoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    private Aluno toEntity(AlunoRequestDTO dto) {
        Aluno aluno = new Aluno();

        aluno.setNome(dto.getNome());
        aluno.setMatricula(dto.getMatricula());
        aluno.setAtivo(dto.getAtivo());

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(dto.getUsuarioId()));

        aluno.setUsuario(usuario);

        return aluno;
    }

    private AlunoResponseDTO toResponseDTO(Aluno aluno) {
        AlunoResponseDTO dto = new AlunoResponseDTO();

        dto.setIdAluno(aluno.getIdAluno());
        dto.setNome(aluno.getNome());
        dto.setMatricula(aluno.getMatricula());
        dto.setAtivo(aluno.getAtivo());
        dto.setUsuarioId(aluno.getUsuario().getIdUsuario());

        return dto;
    }

    public AlunoResponseDTO buscarPorId(Integer id){
        Aluno aluno= alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
        return toResponseDTO(aluno);
    }
    public List<AlunoResponseDTO> listarAluno() {
        return alunoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public AlunoResponseDTO cadastrarAluno(AlunoRequestDTO dto) {
        if (alunoRepository.existsByMatricula(dto.getMatricula())) {
            throw new MatriculaDuplicadoException(dto.getMatricula());
        }

        Aluno aluno = toEntity(dto);
        aluno = alunoRepository.save(aluno);
        return toResponseDTO(aluno);
    }

    public void excluirAluno(Integer id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno com ID " + id + " não encontrado"));

        alunoRepository.delete(aluno);
    }

    public AlunoResponseDTO atualizarAluno(Integer id, AlunoRequestDTO dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno com ID " + id + " não encontrado"));

        if (dto.getNome() != null) {
            aluno.setNome(dto.getNome());
        }
        if (dto.getMatricula() != null) {
            aluno.setMatricula(dto.getMatricula());
        }
        if (dto.getAtivo() != null) {
            aluno.setAtivo(dto.getAtivo());
        }
        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            aluno.setUsuario(usuario);
        }

        Aluno atualizado = alunoRepository.save(aluno);
        return toResponseDTO(atualizado);
    }

}
