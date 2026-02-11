package com.example.cadastroaluno.dto.request;

import jakarta.validation.constraints.NotNull;

public class ProfessorDisciplinaRequestDTO {
    @NotNull(message = "O professor é obrigatório")
    private Integer professorId;
}
