package com.example.cadastroaluno.dto.request;

import com.example.cadastroaluno.validation.OnCreate;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SecretarioAdmRequestDTO {

    @NotNull(message = "O id é obrigatório para atualização", groups = OnCreate.class)
    private Integer idSecretario;

    @NotBlank(message = "O nome é obrigatório", groups = OnCreate.class)
    @Size(max = 100)
    private String nome;

    @NotNull(message = "O status ativo é obrigatório", groups = OnCreate.class)
    private Boolean ativo;

    @NotNull(message = "O usuário é obrigatório", groups = OnCreate.class)
    private Integer usuarioId;

}
