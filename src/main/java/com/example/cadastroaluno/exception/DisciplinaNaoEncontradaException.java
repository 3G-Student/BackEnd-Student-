package com.example.cadastroaluno.exception;

public class DisciplinaNaoEncontradaException extends RuntimeException {
    public DisciplinaNaoEncontradaException(Integer usuarioId) {
        super("Disciplina não encontrada");
    }
}
