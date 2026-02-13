package com.example.cadastroaluno.exception;

public class UsuarioJaPossuiProfessorException extends RuntimeException {
    public UsuarioJaPossuiProfessorException() {
        super("Este usuário já possui um professor");
    }
}
