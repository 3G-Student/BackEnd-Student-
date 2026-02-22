package com.example.cadastroaluno.exception;

public class NomeDuplicadoException extends RuntimeException {
    public NomeDuplicadoException(String nome) {
        super("A disciplina " + nome + " já existe");
    }
}

