package com.example.cadastroaluno.exception;

public class UsuarioJaPossuiSecretarioException extends RuntimeException {
    public UsuarioJaPossuiSecretarioException() {
        super("Este usuário já possui um secretário administrativo");
    }
}
