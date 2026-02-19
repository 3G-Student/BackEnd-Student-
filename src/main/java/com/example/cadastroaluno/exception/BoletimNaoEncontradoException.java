package com.example.cadastroaluno.exception;

public class BoletimNaoEncontradoException extends RuntimeException {
    public BoletimNaoEncontradoException(Integer usuarioId) {
        super("Boletim com o id:" + usuarioId + " não encontrado");
    }
}
