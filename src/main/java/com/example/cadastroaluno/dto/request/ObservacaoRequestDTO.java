package com.example.cadastroaluno.dto.request;

import com.example.cadastroaluno.validation.OnCreate;
import jakarta.persistence.Column;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ObservacaoRequestDTO {

    @NotBlank(message = "A descrição é obrigatória", groups = OnCreate.class)
    private String descricao;

    @FutureOrPresent(message = "A data da observação não pode ser no passado", groups = OnCreate.class)
    @NotNull(message = "A data da observação é obrigatória", groups = OnCreate.class)
    private LocalDate dataObs;

    @NotNull(message = "O aluno é obrigatório", groups = OnCreate.class)
    private Integer alunoId;

    @NotNull(message = "O professor é obrigatório", groups = OnCreate.class)
    private Integer professorId;
}
