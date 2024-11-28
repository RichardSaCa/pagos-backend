package com.banco.pagos_backend.repository;

import com.banco.pagos_backend.model.UsuarioRoleEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Permite manipular información de la base de datos, respecto a la tabla user_role
 */
public interface UsuarioRoleEntityRepository  extends CrudRepository<UsuarioRoleEntity, String> {
}
