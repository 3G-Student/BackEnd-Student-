package com.example.cadastroaluno.repository;

import com.example.cadastroaluno.model.SecretarioAdm;
import com.example.cadastroaluno.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecretarioAdmRepository extends JpaRepository<SecretarioAdm, Integer> {
    boolean existsByUsuario_IdUsuario(Integer usuarioId);

    Optional<SecretarioAdm> findByUsuario(Usuario usuario);
}
