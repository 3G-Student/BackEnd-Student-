package com.example.cadastroaluno.dto.request;

import com.example.cadastroaluno.validation.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DisciplinaRequestDTO {

    @NotNull(message = "O id é obrigatório para atualização", groups = OnCreate.class)
    private Integer idDisciplina;

    @NotBlank(message = "O nome é obrigatório", groups = OnCreate.class)
    @Size(max = 100)
    private String nome;

}
