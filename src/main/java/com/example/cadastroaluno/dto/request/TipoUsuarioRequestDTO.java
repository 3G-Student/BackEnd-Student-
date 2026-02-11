package com.example.cadastroaluno.dto.request;

import com.example.cadastroaluno.validation.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoUsuarioRequestDTO {

    @NotBlank(message = "A descrição é obrigatória", groups = OnCreate.class)
    @Size(max = 100)
    private String descricao;

}
