package com.example.cadastroaluno.service;

import com.example.cadastroaluno.dto.request.TipoUsuarioRequestDTO;
import com.example.cadastroaluno.dto.response.TipoUsuarioResponseDTO;
import com.example.cadastroaluno.model.TipoUsuario;
import com.example.cadastroaluno.repository.TipoUsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoUsuarioService {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    public TipoUsuarioService(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    private TipoUsuario toEntity(TipoUsuarioRequestDTO dto) {
        TipoUsuario tipoUsuario = new TipoUsuario();

        tipoUsuario.setDescricao(dto.getDescricao());

        return tipoUsuario;
    }

    private TipoUsuarioResponseDTO toResponseDTO(TipoUsuario tipoUsuario) {
        TipoUsuarioResponseDTO dto = new TipoUsuarioResponseDTO();

        dto.setDescricao(tipoUsuario.getDescricao());

        return dto;
    }

    public TipoUsuarioResponseDTO buscarPorId(Integer id){
        TipoUsuario tipoUsuario= tipoUsuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TipoUsuario não encontrada"));
        return toResponseDTO(tipoUsuario);
    }
    public List<TipoUsuarioResponseDTO> listarTipoUsuario() {
        return tipoUsuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public TipoUsuarioResponseDTO cadastrarTipoUsuario(TipoUsuarioRequestDTO dto) {
        TipoUsuario tipoUsuario = toEntity(dto);
        tipoUsuario = tipoUsuarioRepository.save(tipoUsuario);
        return toResponseDTO(tipoUsuario);
    }

    public void excluirTipoUsuario(Integer id) {
        TipoUsuario TipoUsuario = tipoUsuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TipoUsuario com ID " + id + " não encontrado"));

        tipoUsuarioRepository.delete(TipoUsuario);
    }

    public TipoUsuarioResponseDTO atualizarTipoUsuario(Integer id, TipoUsuarioRequestDTO dto) {
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TipoUsuario com ID " + id + " não encontrado"));

        if (dto.getDescricao() != null) {
            tipoUsuario.setDescricao(dto.getDescricao());
        }

        TipoUsuario atualizado = tipoUsuarioRepository.save(tipoUsuario);
        return toResponseDTO(atualizado);
    }

}
