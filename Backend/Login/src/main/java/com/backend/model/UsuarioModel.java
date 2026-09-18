package com.backend.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * ==========================================
 * CAPA DE MODELO (MODEL - MVC)
 * ==========================================
 * Esta clase representa la entidad de datos "Usuario" en la base de datos.
 * Define la estructura de la tabla y sus columnas.
 */
@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String rol; // Ejemplo: "ROLE_USER", "ROLE_ADMIN"
}
