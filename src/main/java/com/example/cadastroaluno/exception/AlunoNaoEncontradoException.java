package com.example.cadastroaluno.exception;

public class AlunoNaoEncontradoException extends RuntimeException {
    public AlunoNaoEncontradoException(Integer usuarioId) {
        super("Aluno não encontrado");
    }
}
