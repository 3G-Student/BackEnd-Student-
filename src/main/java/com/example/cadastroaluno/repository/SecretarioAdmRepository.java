package com.example.cadastroaluno.repository;

import com.example.cadastroaluno.model.SecretarioAdm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretarioAdmRepository extends JpaRepository<SecretarioAdm, Integer> {
    boolean existsByUsuario_IdUsuario(Integer usuarioId);
}
