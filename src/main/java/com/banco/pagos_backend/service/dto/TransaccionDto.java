package com.banco.pagos_backend.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransaccionDto {
    private String referencia;
    private BigDecimal monto;
    private String username;
}
