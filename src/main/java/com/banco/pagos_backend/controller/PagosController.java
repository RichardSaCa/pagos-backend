package com.banco.pagos_backend.controller;

import com.banco.pagos_backend.controller.exception.RecursoNoEncontradoExcepcion;
import com.banco.pagos_backend.model.Transacion;
import com.banco.pagos_backend.service.TransacionService;
import com.banco.pagos_backend.service.dto.TransaccionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador que recibe y envía peticiones relacionada con los pagos.
 * Se tiene un método para realizar un pago, y otra para obtener las transacciones de un usuario
 */
@RestController
@RequestMapping("/api/v1/pagos")
public class PagosController {

    private final TransacionService transacionService;

    @Autowired
    public PagosController(TransacionService transacionService){
        this.transacionService = transacionService;
    }

    /**
     * Este método permite realizar un pago, a través de información del mismo.
     * @param transaccionDto permite manipular información de la transacción.
     * @return retorna la transacción, o excepción si hay problemas con el pago.
     */
    @PostMapping("/transaccion")
    public ResponseEntity<Transacion> hacerPago(@RequestBody TransaccionDto transaccionDto) {
        try {
            return ResponseEntity.ok(this.transacionService.hacerPago(transaccionDto));
        } catch (Exception e) {
            throw new RecursoNoEncontradoExcepcion("No se puedo realizar el pago " + e);
        }
    }

    /**
     * Este método permite obtener historial de transacciones de un usuario
     * @param username es necesario para la consulta
     * @return retorna lista del historial, o excepción si es el caso.
     */
    @GetMapping("/transacciones/{username}")
    public ResponseEntity<List<Transacion>> obtenerTransaciones(@PathVariable String username) {
        try {
            return ResponseEntity.ok(this.transacionService.obtenerTransacciones(username));
        } catch (Exception e) {
            throw new RecursoNoEncontradoExcepcion("No se pudo obtener el historial " + e);
        }
    }

}
