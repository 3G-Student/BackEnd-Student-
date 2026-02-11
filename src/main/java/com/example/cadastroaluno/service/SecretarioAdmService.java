package com.example.cadastroaluno.service;

import com.example.cadastroaluno.dto.request.SecretarioAdmRequestDTO;
import com.example.cadastroaluno.dto.response.SecretarioAdmResponseDTO;
import com.example.cadastroaluno.exception.UsuarioNaoEncontradoException;
import com.example.cadastroaluno.model.Aluno;
import com.example.cadastroaluno.model.SecretarioAdm;
import com.example.cadastroaluno.model.Usuario;
import com.example.cadastroaluno.repository.SecretarioAdmRepository;
import com.example.cadastroaluno.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecretarioAdmService {

    private final SecretarioAdmRepository secretarioAdmRepository;
    private final UsuarioRepository usuarioRepository;

    public SecretarioAdmService(SecretarioAdmRepository secretarioAdmRepository, UsuarioRepository usuarioRepository) {
        this.secretarioAdmRepository = secretarioAdmRepository;
        this.usuarioRepository = usuarioRepository;
    }

    private SecretarioAdm toEntity(SecretarioAdmRequestDTO dto) {
        SecretarioAdm secretarioAdm = new SecretarioAdm();

        secretarioAdm.setNome(dto.getNome());
        secretarioAdm.setAtivo(dto.getAtivo());

        Usuario Usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(dto.getUsuarioId()));

        secretarioAdm.setUsuario(Usuario);

        return secretarioAdm;
    }

    private SecretarioAdmResponseDTO toResponseDTO(SecretarioAdm secretarioAdm) {
        SecretarioAdmResponseDTO dto = new SecretarioAdmResponseDTO();

        dto.setIdSecretario(secretarioAdm.getIdSecretario());
        dto.setNome(secretarioAdm.getNome());
        dto.setAtivo(secretarioAdm.getAtivo());
        dto.setUsuarioId(secretarioAdm.getUsuario().getIdUsuario());

        return dto;
    }

    public SecretarioAdmResponseDTO buscarPorId(Integer id){
        SecretarioAdm SecretarioAdm= secretarioAdmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SecretarioAdm não encontrado"));
        return toResponseDTO(SecretarioAdm);
    }
    public List<SecretarioAdmResponseDTO> listarSecretarioAdm() {
        return secretarioAdmRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public SecretarioAdmResponseDTO cadastrarSecretarioAdm(SecretarioAdmRequestDTO dto) {
        SecretarioAdm secretarioAdm = toEntity(dto);
        secretarioAdm = secretarioAdmRepository.save(secretarioAdm);
        return toResponseDTO(secretarioAdm);
    }

    public void excluirSecretarioAdm(Integer id) {
        SecretarioAdm secretarioAdm = secretarioAdmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SecretarioAdm com ID " + id + " não encontrado"));

        secretarioAdmRepository.delete(secretarioAdm);
    }

    public SecretarioAdmResponseDTO atualizarSecretarioAdm(Integer id, SecretarioAdmRequestDTO dto) {
        SecretarioAdm secretarioAdm = secretarioAdmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SecretarioAdm com ID " + id + " não encontrado"));

        if (dto.getNome() != null) {
            secretarioAdm.setNome(dto.getNome());
        }
        if (dto.getAtivo() != null) {
            secretarioAdm.setAtivo(dto.getAtivo());
        }
        if (dto.getUsuarioId() != null) {
            Usuario Usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            secretarioAdm.setUsuario(Usuario);

        }

        SecretarioAdm atualizado = secretarioAdmRepository.save(secretarioAdm);
        return toResponseDTO(atualizado);
    }


}
