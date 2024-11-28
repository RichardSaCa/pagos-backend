package com.banco.pagos_backend.util;

import lombok.Data;

import java.util.List;

/**
 * Esta es una clase para modelar el Token que será enviado a través de la petición de la api.
 */
@Data
public class Token {
    private String access_token;
    private String username;
}
