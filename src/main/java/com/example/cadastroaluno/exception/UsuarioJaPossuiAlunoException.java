package com.example.cadastroaluno.exception;

public class UsuarioJaPossuiAlunoException extends RuntimeException {
    public UsuarioJaPossuiAlunoException() {
        super("Este usuário já possui um aluno");
    }
}
