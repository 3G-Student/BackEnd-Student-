package com.example.cadastroaluno.exception;

public class TipoUsuarioInvalidoException extends RuntimeException {
    public TipoUsuarioInvalidoException() {
        super("O usuário informado não é do tipo aluno");
    }
}
