package com.example.cadastroaluno.controller;

import com.example.cadastroaluno.dto.response.ProfessorDisciplinaResponseDTO;
import com.example.cadastroaluno.service.ProfessorDisciplinaService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ProfessorDisciplina")
@AllArgsConstructor
public class ProfessorDisciplinaController {

    private final ProfessorDisciplinaService service;

    @GetMapping
    public List<ProfessorDisciplinaResponseDTO> listarTodos() {
        return service.listarTodos();
    }

}

