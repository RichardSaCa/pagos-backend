package com.banco.pagos_backend.repository;

import com.banco.pagos_backend.model.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 * Permite manipular información de la base de datos, respecto a la tabla usuarios
 */
public interface UserRepository extends CrudRepository<Usuario, String> {

}
