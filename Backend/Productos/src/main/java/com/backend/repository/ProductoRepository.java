package com.backend.repository;

import com.backend.model.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ==========================================
 * CAPA DE REPOSITORIO (ACCESO A DATOS - MODEL)
 * ==========================================
 */
@Repository
public interface ProductoRepository extends JpaRepository<ProductoModel, Long> {

    // Buscar productos por categoría
    List<ProductoModel> findByCategoria(String categoria);

    // Buscar productos cuyo nombre contenga el texto buscado
    List<ProductoModel> findByNombreContainingIgnoreCase(String nombre);
}
