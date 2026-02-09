package com.example.cadastroaluno.exception;

public class EmailDuplicadoException extends RuntimeException {
    public EmailDuplicadoException(String email) {
        super("O email '" + email + "' já está cadastrado.");
    }
}

