package com.example.cadastroaluno.repository;

import com.example.cadastroaluno.dto.response.PerfilUsuarioResponseDTO;
import com.example.cadastroaluno.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);

    @Query(value = """
        SELECT 
            COALESCE(a.nome, p.nome, s.nome) AS nome,
            u.email
        FROM usuario u
        LEFT JOIN aluno a ON a.usuario_id = u.id_usuario
        LEFT JOIN professor p ON p.usuario_id = u.id_usuario
        LEFT JOIN secretario_adm s ON s.usuario_id = u.id_usuario
        WHERE u.id_usuario = :id
        """, nativeQuery = true)
    PerfilUsuarioResponseDTO buscarPerfilUsuario(@Param("id") Integer id);

    @Query("""
        SELECT o FROM Usuario o
        WHERE o.email = :email
        """)
    Optional<Usuario> findByLogin(@Param("email") String email);

}
