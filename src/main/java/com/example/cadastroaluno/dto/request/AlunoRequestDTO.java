package com.example.cadastroaluno.dto.request;

import com.example.cadastroaluno.validation.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class AlunoRequestDTO {

    @NotBlank(message = "O nome é obrigatório", groups = OnCreate.class)
    @Size(max = 100)
    private String nome;

    @NotBlank(message = "A matrícula é obrigatória", groups = OnCreate.class)
    private String matricula;

    @NotNull(message = "O status ativo é obrigatório", groups = OnCreate.class)
    private Boolean ativo;

    @NotNull(message = "O usuário é obrigatório", groups = OnCreate.class)
    private Integer usuarioId;
}
