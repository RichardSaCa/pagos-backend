package com.banco.pagos_backend.service;

import com.banco.pagos_backend.model.Usuario;
import com.banco.pagos_backend.service.dto.UsuarioDto;

/**
 * Interfaz con métodos abstractos relacionados con el usuario, para este caso se tiene un método,
 * que permite crear un usuario.
 */
public interface UsuarioService {
    public Usuario crearUsuario(UsuarioDto usuarioDto);
}
