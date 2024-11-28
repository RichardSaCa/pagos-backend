package com.banco.pagos_backend.service.dto;

import lombok.Data;

@Data
public class UsuarioDto {
    private String username;
    private String nombre;
    private String email;
    private String password;
    private String role; // ADMIN - CUSTOMER
}
