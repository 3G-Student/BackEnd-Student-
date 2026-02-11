package com.example.cadastroaluno.service;

import com.example.cadastroaluno.dto.request.UsuarioRequestDTO;
import com.example.cadastroaluno.dto.response.UsuarioResponseDTO;
import com.example.cadastroaluno.exception.EmailDuplicadoException;
import com.example.cadastroaluno.exception.TipoUsuarioNaoEncontradoException;
import com.example.cadastroaluno.model.TipoUsuario;
import com.example.cadastroaluno.model.Usuario;
import com.example.cadastroaluno.repository.TipoUsuarioRepository;
import com.example.cadastroaluno.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, TipoUsuarioRepository tipoUsuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setEmail(dto.getEmail());

        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(dto.getTipoId())
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException(dto.getTipoId()));

        usuario.setTipoUsuario(tipoUsuario);

        return usuario;
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setEmail(usuario.getEmail());
        dto.setTipoId(usuario.getTipoUsuario().getIdTipo());

        return dto;
    }

    public UsuarioResponseDTO buscarPorId(Integer id){
        Usuario usuario= usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        return toResponseDTO(usuario);
    }
    public List<UsuarioResponseDTO> listarUsuario() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new EmailDuplicadoException(dto.getEmail());
        }

        Usuario usuario = toEntity(dto);
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario = usuarioRepository.save(usuario);
        return toResponseDTO(usuario);
    }

    public void excluirUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario com ID " + id + " não encontrado"));

        usuarioRepository.delete(usuario);
    }

    public UsuarioResponseDTO atualizarUsuario(Integer id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario com ID " + id + " não encontrado"));

        if (dto.getEmail() != null) {
            usuario.setEmail(dto.getEmail());
        }
        if (dto.getSenha() != null) {
            usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        }
        if (dto.getTipoId() != null) {
            TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(dto.getTipoId())
                    .orElseThrow(() -> new RuntimeException("Tipo Usuário não encontrado"));
            usuario.setTipoUsuario(tipoUsuario);

        }

        Usuario atualizado = usuarioRepository.save(usuario);
        return toResponseDTO(atualizado);
    }

}
