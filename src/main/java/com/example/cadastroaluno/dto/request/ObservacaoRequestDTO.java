package com.example.cadastroaluno.dto.request;

import com.example.cadastroaluno.validation.OnCreate;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ObservacaoRequestDTO {

    @NotNull(message = "O id é obrigatório para atualização", groups = OnCreate.class)
    private Integer idObservacao;

    @NotBlank(message = "A descrição é obrigatória", groups = OnCreate.class)
    private String descricao;

    @NotNull(message = "A data da observação é obrigatória", groups = OnCreate.class)
    private Date dataObs;

    @NotNull(message = "O aluno é obrigatório", groups = OnCreate.class)
    private Integer alunoId;

    @NotNull(message = "O professor é obrigatório", groups = OnCreate.class)
    private Integer professorId;
}
