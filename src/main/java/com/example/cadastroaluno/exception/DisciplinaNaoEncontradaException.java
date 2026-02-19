package com.example.cadastroaluno.exception;

public class DisciplinaNaoEncontradaException extends RuntimeException {
    public DisciplinaNaoEncontradaException(Integer usuarioId) {
        super("Disciplina com o id: " + usuarioId + "não encontrada");
    }
}
