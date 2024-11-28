package com.banco.pagos_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


import java.io.Serializable;
import java.util.List;

/**
 * Modela la tabla usuarios, también muestra las relaciones que tiene con otras tablas.
 */
@Entity
@Data
@ToString(exclude = {"transaciones", "roles"})
@Table(name = "usuarios")
//@JsonIgnoreProperties({"password"})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String username;


    private String nombre;


    private String email;


    private String password;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    private List<Transacion> transaciones;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<UsuarioRoleEntity> roles;

    public Usuario() {
    }
}
