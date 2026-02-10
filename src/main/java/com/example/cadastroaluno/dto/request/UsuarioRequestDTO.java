package com.example.cadastroaluno.dto.request;

import com.example.cadastroaluno.validation.OnCreate;
import jakarta.validation.constraints.Email;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class UsuarioRequestDTO {
//    message = "O id é obrigatório para atualização",
//    @NotNull(groups = OnCreate.class)
    private Integer idUsuario;

    @NotBlank(message = "O email é obrigatório", groups = OnCreate.class)
    @Email(message = "O email deve ser válido", groups = OnCreate.class)
    private String email;

    @NotBlank(message = "A senha é obrigatória", groups = OnCreate.class)
    private String senha;

    @NotNull(message = "O tipo de usuário é obrigatório", groups = OnCreate.class)
    private Integer tipoId;

}
