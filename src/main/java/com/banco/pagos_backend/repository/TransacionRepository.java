package com.banco.pagos_backend.repository;

import com.banco.pagos_backend.model.Transacion;;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Esta interfaz utiliza spring jpa, para facilitar el acceso a información de la base de datos
 */
public interface TransacionRepository extends CrudRepository<Transacion, Long> {

    /**
     * Esta consulta obtiene el historial de transacciones por un usuario determinado
     * @param username
     * @return retorna la lista del historial.
     * La consulta se hace con un query estándar que usa jpa.
     */
    @Query(value = "SELECT t FROM Transacion t WHERE t.user.username = :username")
    List<Transacion> obtenerPorUsername(String username);
}
