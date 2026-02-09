package com.example.cadastroaluno.dto.request;

import com.example.cadastroaluno.validation.OnCreate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BoletimRequestDTO {

    @NotNull(message = "O id é obrigatório para atualização", groups = OnCreate.class)
    private Integer idBoletim;

    @NotNull(message = "A disciplina é obrigatória", groups = OnCreate.class)
    private Integer disciplinaId;

    @NotNull(message = "O aluno é obrigatório", groups = OnCreate.class)
    private Integer alunoId;

    @NotNull(message = "A nota 1 é obrigatória", groups = OnCreate.class)
    private Double nota1;

    @NotNull(message = "A nota 2 é obrigatória", groups = OnCreate.class)
    private Double nota2;

    private Double media;

}
