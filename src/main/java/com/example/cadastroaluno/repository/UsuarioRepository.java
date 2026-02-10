package com.example.cadastroaluno.repository;

import com.example.cadastroaluno.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByEmail(String email);
    @Query(value = """
        SELECT a.nome, u.email, u.senha
        FROM usuario u
        JOIN aluno a ON a.usuario_id = u.id_usuario
        WHERE u.id_usuario = :id
        """, nativeQuery = true)
    Object[] buscarPerfilUsuario(@Param("id") Integer id);

    Optional<Usuario> findByEmail(String email);

}
