package com.example.cadastroaluno.exception;

public class ObservacaoNaoEncontradaException extends RuntimeException {
    public ObservacaoNaoEncontradaException(Integer usuarioId) {
        super("Observação com id: " + usuarioId + " não encontrada");
    }
}
