package com.example.cadastroaluno.dto.request;

import com.example.cadastroaluno.validation.OnCreate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BoletimRequestDTO {

    @NotNull(message = "A disciplina é obrigatória", groups = OnCreate.class)
    private Integer disciplinaId;

    @NotNull(message = "O aluno é obrigatório", groups = OnCreate.class)
    private Integer alunoId;

    @NotNull(message = "A nota 1 é obrigatória", groups = OnCreate.class)
    @Min(value = 0, message = "A nota 1 não pode ser menor que 0")
    @Max(value = 10, message = "A nota 1 não pode ser maior que 10")
    private Double nota1;

    @NotNull(message = "A nota 2 é obrigatória", groups = OnCreate.class)
    @Min(value = 0, message = "A nota 2 não pode ser menor que 0")
    @Max(value = 10, message = "A nota 2 não pode ser maior que 10")
    private Double nota2;

}
