package com.backend.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * ==========================================
 * CAPA DE MODELO (MODEL - MVC)
 * ==========================================
 * Clase entidad que representa la tabla 'productos' en la base de datos.
 */
@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer stock;

    private String imagenUrl;

    private String categoria;
}
