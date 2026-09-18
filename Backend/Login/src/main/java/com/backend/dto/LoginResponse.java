package com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ==========================================
 * CAPA DE VISTA / DTO (DATA TRANSFER OBJECT)
 * ==========================================
 * Representa la "Vista" o datos de respuesta estructurados en JSON que el cliente 
 * recibirá tras intentar iniciar sesión o registrarse.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private boolean exito;
    private String mensaje;
    private String email;
    private String nombre;
    private String rol;
    private String token; // Espacio reservado para token JWT o de sesión
}
