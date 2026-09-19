package com.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ==========================================
 * CAPA DE VISTA / DTO (MICROSOFT MSAL)
 * ==========================================
 * Datos enviados desde el Frontend tras una autenticación exitosa en MSAL.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MicrosoftLoginRequest {

    @NotBlank(message = "El token de Microsoft (idToken/accessToken) es obligatorio")
    private String token;

    private String email;
    private String nombre;
}
