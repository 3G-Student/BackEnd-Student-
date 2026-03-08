package com.example.cadastroaluno.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProfessorDisciplinaRequestDTO {
    @NotNull(message = "O professor é obrigatório")
    private Integer professorId;

    @NotNull(message = "A disciplina é obrigatório")
    private Integer disciplinaId;
}
