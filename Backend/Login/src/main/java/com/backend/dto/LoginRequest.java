package com.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ==========================================
 * CAPA DE VISTA / DTO (DATA TRANSFER OBJECT)
 * ==========================================
 * Este objeto encapsula los datos que el usuario envía desde la Vista (Frontend)
 * para realizar la petición de Inicio de Sesión.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe ser un email válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;
}
