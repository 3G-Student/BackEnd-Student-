package com.example.cadastroaluno.service;

import com.example.cadastroaluno.dto.request.AlunoRequestDTO;
import com.example.cadastroaluno.dto.response.AlunoResponseDTO;
import com.example.cadastroaluno.dto.response.DisciplinaResponseDTO;
import com.example.cadastroaluno.exception.*;
import com.example.cadastroaluno.model.Aluno;
import com.example.cadastroaluno.model.Disciplina;
import com.example.cadastroaluno.model.Usuario;
import com.example.cadastroaluno.repository.AlunoRepository;
import com.example.cadastroaluno.repository.BoletimRepository;
import com.example.cadastroaluno.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final UsuarioRepository usuarioRepository;
    private final BoletimRepository boletimRepository;

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

    private DisciplinaResponseDTO toDisciplinaResponseDTO(Disciplina disciplina) {
        DisciplinaResponseDTO dto = new DisciplinaResponseDTO();

        dto.setIdDisciplina(disciplina.getIdDisciplina());
        dto.setNome(disciplina.getNome());

        return dto;
    }

    public AlunoResponseDTO buscarPorId(Integer id){
        Aluno aluno= alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException(id));
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
            throw new MatriculaDuplicadaException(dto.getMatricula());
        }

        if (alunoRepository.existsByUsuario_IdUsuario(dto.getUsuarioId())) {
            throw new UsuarioJaPossuiAlunoException();
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(dto.getUsuarioId()));

        if (usuario.getTipoUsuario().getIdTipo() != 1) {
            throw new TipoUsuarioInvalidoException();
        }

        Aluno aluno = toEntity(dto);
        aluno.setUsuario(usuario);
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

    //Métodos derivados
    public List<AlunoResponseDTO> listarPorAtivo(Boolean ativo) {
        return alunoRepository.findByAtivo(ativo)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    //Query

    public List<DisciplinaResponseDTO> listarDisciplinasPorAluno(Integer idAluno) {
        return boletimRepository.findDisciplinasPorAluno(idAluno)
                .stream()
                .map(this::toDisciplinaResponseDTO)
                .toList();
    }
}
