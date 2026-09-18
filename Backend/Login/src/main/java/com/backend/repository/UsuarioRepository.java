package com.backend.repository;

import com.backend.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ==========================================
 * CAPA DE REPOSITORIO (ACCESO A DATOS - MODEL)
 * ==========================================
 * Esta interfaz extiende JpaRepository, lo que nos otorga métodos automáticos 
 * como save(), findById(), findAll(), deleteById(), etc.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    // Método personalizado para buscar un usuario por su correo electrónico
    Optional<UsuarioModel> findByEmail(String email);

    // Método personalizado para verificar si ya existe un usuario registrado con ese email
    boolean existsByEmail(String email);
}
