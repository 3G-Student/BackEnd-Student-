package com.example.cadastroaluno.exception;

public class MatriculaDuplicadaException extends RuntimeException {
    public MatriculaDuplicadaException(String matricula) {
        super("Usuário com matricula " + matricula + " já existente.");
    }
}
