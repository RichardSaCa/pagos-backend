package com.banco.pagos_backend.controller;

import com.banco.pagos_backend.config.JwtUtil;
import com.banco.pagos_backend.controller.exception.RecursoNoEncontradoExcepcion;
import com.banco.pagos_backend.model.Usuario;
import com.banco.pagos_backend.service.UsuarioService;
import com.banco.pagos_backend.service.dto.LoginDto;


import com.banco.pagos_backend.service.dto.UsuarioDto;
import com.banco.pagos_backend.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Este controlador se usa para el proceso de autenticación, tanto para iniciar sesión como para crear un usuario
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioService usuarioService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    /**
     * Método para iniciar sesión
     * @param loginDto me permite manipular la información del login.
     * @return retorna un token con información del jwt y usuario, es posible también agregar más información
     * dependiendo de las necesidades que se tengan.
     */
    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody LoginDto loginDto) {
        try{
            UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            Authentication authentication = this.authenticationManager.authenticate(login);

            String jwt = this.jwtUtil.create(loginDto.getUsername());
            /**
             * agregando el token que será enviado
             */
            Token token = new Token();
            token.setAccess_token(jwt);
            token.setUsername(loginDto.getUsername());

            return ResponseEntity.ok(token);
        }catch (Exception e){
            throw new RecursoNoEncontradoExcepcion("Usuario no se pudo validar "+e);
        }

    }

    /**
     * Este método permite crear un usuario
     * @param usuarioDto
     * @return retorna el usuario creado, siempre y cuando no se tengan problemas
     */
    @PostMapping("/create")
    public ResponseEntity<Usuario> createUser(@RequestBody UsuarioDto usuarioDto) {
        try{
            System.out.println("entry");
            return ResponseEntity.ok(this.usuarioService.crearUsuario(usuarioDto));
        }catch (Exception e){
            throw new RecursoNoEncontradoExcepcion("Usuario no se pudo validar "+e);
        }

    }
}
