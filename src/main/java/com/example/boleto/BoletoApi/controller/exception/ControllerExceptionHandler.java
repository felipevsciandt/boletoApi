package com.example.boleto.BoletoApi.controller.exception;

import com.example.boleto.BoletoApi.service.exception.BoletoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

public class ControllerExceptionHandler {
    @ExceptionHandler(BoletoNotFoundException.class)
    public ResponseEntity<StandardError> boletoNotFoundException(BoletoNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NO_CONTENT;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Erro: Boleto não cadastrado com Id informado");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
