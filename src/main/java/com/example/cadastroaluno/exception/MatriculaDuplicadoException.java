package com.example.cadastroaluno.exception;

public class MatriculaDuplicadoException extends RuntimeException {
    public MatriculaDuplicadoException(String matricula) {
        super("Usuário já cadastrado!");
    }
}
