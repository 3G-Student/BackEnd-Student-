package com.example.cadastroaluno.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(Integer usuarioId) {
        super("Usuário com id: " + usuarioId + " não encontrado");
    }
}
