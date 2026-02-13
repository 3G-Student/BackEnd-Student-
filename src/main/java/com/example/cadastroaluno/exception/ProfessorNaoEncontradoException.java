package com.example.cadastroaluno.exception;

public class ProfessorNaoEncontradoException extends RuntimeException {
    public ProfessorNaoEncontradoException(Integer usuarioId) {
        super("Professor com id " + usuarioId +" não encontrado");
    }
}
