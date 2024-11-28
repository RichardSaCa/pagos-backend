package com.banco.pagos_backend.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Esta entidad modela una transacción, que es una tabla de la base de datos.
 * Se usa @Data para métodos get, set y constructivo.
 * Se realizan unas exclusiones como @ToString, para evitar problemas comunicación bidirecional.
 */
@Entity
@Data
@ToString(exclude = "user")
@Table(name = "transaciones")
public class Transacion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_transacion;

    private String referencia;

    private BigDecimal monto;

    @Column(name = "granted_date", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime grantedDate;
    /**
     * Relación de muchos a uno de la base de datos.
     */
    @ManyToOne
    @JoinColumn(name="username")
    @JsonManagedReference
    @JsonIgnore
    private Usuario user;

}
