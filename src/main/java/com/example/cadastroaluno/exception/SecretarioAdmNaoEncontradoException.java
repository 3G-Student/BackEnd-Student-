package com.example.cadastroaluno.exception;

public class SecretarioAdmNaoEncontradoException extends RuntimeException {
    public SecretarioAdmNaoEncontradoException(Integer usuarioId) {
        super("Aecretário com id: " + usuarioId + " não encontrado");
    }
}
