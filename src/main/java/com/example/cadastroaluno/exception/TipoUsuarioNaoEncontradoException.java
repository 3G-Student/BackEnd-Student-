package com.example.cadastroaluno.exception;

public class TipoUsuarioNaoEncontradoException extends RuntimeException {
    public TipoUsuarioNaoEncontradoException(Integer usuarioId) {
        super("Tipo Usuário não encontrado");
    }
}
