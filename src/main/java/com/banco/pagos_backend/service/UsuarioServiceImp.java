package com.banco.pagos_backend.service;

import com.banco.pagos_backend.model.Usuario;
import com.banco.pagos_backend.model.UsuarioRoleEntity;
import com.banco.pagos_backend.repository.UserRepository;
import com.banco.pagos_backend.repository.UsuarioRoleEntityRepository;
import com.banco.pagos_backend.service.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Esta es la lógica de negocio relacionada con el usuario, implementa la interfaz con métodos
 * abstractos.
 */
@Service
public class UsuarioServiceImp implements UsuarioService{
    /**
     * Se tiene los repositorios para user y usuarioRol, estas permiten acceder a la base de datos
     */
    private final UserRepository userRepository;
    private final UsuarioRoleEntityRepository usuarioRoleEntityRepository;

    @Autowired
    public UsuarioServiceImp(UserRepository userRepository, UsuarioRoleEntityRepository usuarioRoleEntityRepository){
        this.userRepository = userRepository;
        this.usuarioRoleEntityRepository = usuarioRoleEntityRepository;
    }

    /**
     * Este método crea un usuario en la base de datos, es necesario utilizar usuarioDto el cual me facilita
     * la manipulación de la información del usuario.
     * @param usuarioDto
     * @return
     */
    @Override
    public Usuario crearUsuario(UsuarioDto usuarioDto) {
        /**
         * Proceso para encriptar la contraseña
         */
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        /**
         * Se separa espacio en memoria del usuario, luego se establecen sus atributos para guardarlos en la base
         * de datos
         */
        Usuario user = new Usuario();
        user.setUsername(usuarioDto.getUsername());
        user.setNombre(usuarioDto.getNombre());
        user.setEmail(usuarioDto.getEmail());
        user.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
        Usuario newUser = this.userRepository.save(user);
        /**
         * Se llama al método para guardar al usuario que relaciona al rol.
         */
        guardarRoleEntity(usuarioDto, newUser);
        return newUser;
    }

    /**
     * Método para guardar el usuario relacionado con el rol.
     * @param usuarioDto
     * @param user
     */
    public void guardarRoleEntity(UsuarioDto usuarioDto, Usuario user){
        LocalDateTime fechaActual =  LocalDateTime.now(ZoneId.of("America/Bogota"));
        UsuarioRoleEntity userole = new UsuarioRoleEntity();
        userole.setUsername(usuarioDto.getUsername());
        userole.setRole(usuarioDto.getRole());
        userole.setUser(user);
        userole.setGrantedDate(fechaActual);
        this.usuarioRoleEntityRepository.save(userole);
    }
}
