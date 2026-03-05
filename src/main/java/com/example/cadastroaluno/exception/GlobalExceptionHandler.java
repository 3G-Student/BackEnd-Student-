package com.example.cadastroaluno.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlunoNaoEncontradoException.class)
    public ResponseEntity<String> tratarAlunoNaoEncontrado(AlunoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<String> tratarUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProfessorNaoEncontradoException.class)
    public ResponseEntity<String> tratarProfessorNaoEncontrado(ProfessorNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DisciplinaNaoEncontradaException.class)
    public ResponseEntity<String> tratarDisciplinaNaoEncontrada(DisciplinaNaoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(TipoUsuarioNaoEncontradoException.class)
    public ResponseEntity<String> tratarTipoUsuarioNaoEncontrado(TipoUsuarioNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BoletimNaoEncontradoException.class)
    public ResponseEntity<String> tratarBoletimNaoEncontrado(BoletimNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ObservacaoNaoEncontradaException.class)
    public ResponseEntity<String> tratarObservacaoNaoEncontrada(ObservacaoNaoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(SecretarioAdmNaoEncontradoException.class)
    public ResponseEntity<String> tratarSecretarioAdmNaoEncontrado(SecretarioAdmNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(EmailDuplicadoException.class)
    public ResponseEntity<String> tratarEmailDuplicado(EmailDuplicadoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NomeDuplicadoException.class)
    public ResponseEntity<String> tratarNomeDuplicado(NomeDuplicadoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MatriculaDuplicadaException.class)
    public ResponseEntity<String> tratarMatriculaDuplicada(MatriculaDuplicadaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> tratarErroGeral(Exception ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("Erro interno no servidor");
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            erros.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(UsuarioJaPossuiAlunoException.class)
    public ResponseEntity<String> tratarUsuarioJaPossuiAluno(UsuarioJaPossuiAlunoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TipoUsuarioInvalidoException.class)
    public ResponseEntity<String> tratarTipoUsuarioInvalido(TipoUsuarioInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioJaPossuiProfessorException.class)
    public ResponseEntity<String> tratarUsuarioJaPossuiProfessor(UsuarioJaPossuiProfessorException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioJaPossuiSecretarioException.class)
    public ResponseEntity<String> tratarUsuarioJaPossuiSecretario(UsuarioJaPossuiSecretarioException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


}
