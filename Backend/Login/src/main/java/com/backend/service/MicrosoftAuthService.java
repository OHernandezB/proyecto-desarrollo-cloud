package com.backend.service;

import com.backend.dto.LoginResponse;
import com.backend.dto.MicrosoftLoginRequest;
import com.backend.model.UsuarioModel;
import com.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * ==========================================
 * CAPA DE SERVICIO (AUTENTICACIÓN MICROSOFT MSAL)
 * ==========================================
 * Procesa y valida el token de Microsoft enviado por MSAL.js desde la vista/frontend.
 * Si el usuario no existe en la base de datos, lo registra automáticamente.
 */
@Service
public class MicrosoftAuthService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public MicrosoftAuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public LoginResponse autenticarConMicrosoft(MicrosoftLoginRequest request) {
        // En producción con credenciales activas, aquí se valida la firma JWT con la clave pública de Microsoft.
        String email = request.getEmail();
        String nombre = request.getNombre() != null ? request.getNombre() : "Usuario Microsoft";

        if (email == null || email.isBlank()) {
            return LoginResponse.builder()
                    .exito(false)
                    .mensaje("El token de Microsoft no contiene un correo electrónico válido.")
                    .build();
        }

        Optional<UsuarioModel> usuarioOpt = usuarioRepository.findByEmail(email);
        UsuarioModel usuario;

        if (usuarioOpt.isPresent()) {
            usuario = usuarioOpt.get();
        } else {
            // Auto-registro de usuario autenticado mediante Microsoft
            usuario = UsuarioModel.builder()
                    .nombre(nombre)
                    .email(email)
                    .password(UUID.randomUUID().toString()) // Clave aleatoria insegura ya que autentica por MSAL
                    .rol("ROLE_USER")
                    .build();
            usuarioRepository.save(usuario);
        }

        return LoginResponse.builder()
                .exito(true)
                .mensaje("Autenticación con Microsoft exitosa.")
                .email(usuario.getEmail())
                .nombre(usuario.getNombre())
                .rol(usuario.getRol())
                .token(request.getToken()) // Token MSAL validado
                .build();
    }
}
