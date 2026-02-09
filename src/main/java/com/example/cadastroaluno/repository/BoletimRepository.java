package com.example.cadastroaluno.repository;

import com.example.cadastroaluno.model.Boletim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoletimRepository extends JpaRepository<Boletim, Integer> {
}
