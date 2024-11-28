package com.banco.pagos_backend.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Esta excepción me permite entregar respuesta de algún recurso no encontrado
 * Enviando una petición accepted
 */
@ResponseStatus(value = HttpStatus.ACCEPTED)
public class RecursoEncontrado extends RuntimeException{
    public RecursoEncontrado(String mensaje){
        super(mensaje);
    }
}