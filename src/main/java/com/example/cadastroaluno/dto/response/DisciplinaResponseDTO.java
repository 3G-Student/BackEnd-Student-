package com.example.cadastroaluno.dto.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class DisciplinaResponseDTO {

    private Integer idDisciplina;

    private String nome;

}
