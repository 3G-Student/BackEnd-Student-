package com.example.cadastroaluno.service;

import com.example.cadastroaluno.dto.request.UsuarioLoginDTO;
import com.example.cadastroaluno.dto.request.UsuarioRequestDTO;
import com.example.cadastroaluno.dto.request.UsuarioUpdateRequestDTO;
import com.example.cadastroaluno.dto.response.LoginResponseDTO;
import com.example.cadastroaluno.dto.response.PerfilUsuarioResponseDTO;
import com.example.cadastroaluno.dto.response.UsuarioResponseDTO;
import com.example.cadastroaluno.dto.response.UsuarioUpdateResponseDTO;
import com.example.cadastroaluno.exception.EmailDuplicadoException;
import com.example.cadastroaluno.exception.TipoUsuarioNaoEncontradoException;
import com.example.cadastroaluno.exception.UsuarioNaoEncontradoException;
import com.example.cadastroaluno.model.SecretarioAdm;
import com.example.cadastroaluno.model.TipoUsuario;
import com.example.cadastroaluno.model.Usuario;
import com.example.cadastroaluno.repository.*;
import com.example.cadastroaluno.security.CustomDetailsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomDetailsService customDetailsService;

    @Autowired
    private JwtService jwtService;

    private final UsuarioRepository usuarioRepository;
    private final SecretarioAdmRepository secretarioAdmRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;
    private final PasswordEncoder passwordEncoder;

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

    private UsuarioUpdateResponseDTO toResponseUpdateDTO(Usuario usuario) {
        UsuarioUpdateResponseDTO dto = new UsuarioUpdateResponseDTO();

        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setEmail(usuario.getEmail());

        return dto;
    }

    public UsuarioResponseDTO buscarPorId(Integer id){
        Usuario usuario= usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
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

    public UsuarioUpdateResponseDTO atualizarUsuario(Integer id, UsuarioUpdateRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario com ID " + id + " não encontrado"));

        if (dto.getEmail() != null) {
            usuario.setEmail(dto.getEmail());
        }
        if (dto.getSenha() != null) {
            usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        Usuario atualizado = usuarioRepository.save(usuario);
        return toResponseUpdateDTO(atualizado);
    }

    public PerfilUsuarioResponseDTO buscarPerfilUsuario(Integer id) {
        return usuarioRepository.buscarPerfilUsuario(id);
    }


    public LoginResponseDTO validarLogin(UsuarioLoginDTO loginDTO) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getSenha()
                )
        );

        UserDetails userDetails =
                customDetailsService.loadUserByUsername(loginDTO.getEmail());

        String token = jwtService.gerarToken(userDetails);

        Usuario usuario = usuarioRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow();

        Integer idProfessor = null;
        Integer idAluno = null;
        Integer idSecretario = null;

        String tipo = usuario.getTipoUsuario().getDescricao();

        if (tipo.equals("PROFESSOR")) {
            idProfessor = professorRepository
                    .findByUsuario(usuario)
                    .orElseThrow()
                    .getIdProfessor();
        }

        if (tipo.equals("ALUNO")) {
            idAluno = alunoRepository
                    .findByUsuario(usuario)
                    .orElseThrow()
                    .getIdAluno();
        }

        if (tipo.equals("SECRETARIO")) {
            idSecretario = secretarioAdmRepository
                    .findByUsuario(usuario)
                    .orElseThrow()
                    .getIdSecretario();
        }

        return new LoginResponseDTO(
                usuario.getIdUsuario(),
                usuario.getTipoUsuario().getIdTipo(),
                idProfessor,
                idAluno,
                idSecretario,
                token
        );
    }

    public UsuarioResponseDTO atualizarSenha(Integer id, String novaSenha) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario com ID " + id + " não encontrado"));

        String senhaCriptografada = passwordEncoder.encode(novaSenha);
        usuario.setSenha(senhaCriptografada);

        Usuario atualizado = usuarioRepository.save(usuario);
        return toResponseDTO(atualizado);
    }

}
