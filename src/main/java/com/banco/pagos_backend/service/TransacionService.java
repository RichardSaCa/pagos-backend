package com.banco.pagos_backend.service;

import com.banco.pagos_backend.model.Transacion;
import com.banco.pagos_backend.service.dto.TransaccionDto;

import java.util.List;

/**
 * Interfaz que muestra métodos abstractos para lo relacionado con transacción.
 */
public interface TransacionService {
    /**
     * Método abstracto para hacer un pago.
     * @param tranferenciaDto
     * @return
     */
    public Transacion hacerPago(TransaccionDto tranferenciaDto);

    /**
     * Método abstracto para obtener todas las transacciones.
     * @param username
     * @return
     */
    public List<Transacion> obtenerTransacciones(String username);
}
