package com.backend.service;

import com.backend.dto.LoginRequest;
import com.backend.dto.LoginResponse;
import com.backend.dto.RegisterRequest;
import com.backend.model.UsuarioModel;
import com.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ==========================================
 * CAPA DE SERVICIO (LÓGICA DE NEGOCIO)
 * ==========================================
 * El Servicio contiene las reglas de negocio de la aplicación. 
 * Se comunica directamente con la capa de Repositorio (Modelo) para consultar y guardar datos,
 * y le devuelve el resultado procesado al Controlador.
 */
@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Procesa la autenticación de un usuario existente.
     */
    public LoginResponse login(LoginRequest request) {
        Optional<UsuarioModel> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());

        if (usuarioOpt.isEmpty()) {
            return LoginResponse.builder()
                    .exito(false)
                    .mensaje("El correo electrónico no está registrado.")
                    .build();
        }

        UsuarioModel usuario = usuarioOpt.get();

        // Verificar la contraseña coincida con el hash almacenado
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            return LoginResponse.builder()
                    .exito(false)
                    .mensaje("Contraseña incorrecta.")
                    .build();
        }

        return LoginResponse.builder()
                .exito(true)
                .mensaje("Inicio de sesión exitoso.")
                .email(usuario.getEmail())
                .nombre(usuario.getNombre())
                .rol(usuario.getRol())
                .token("mock-jwt-token-ejemplo")
                .build();
    }

    /**
     * Registra un nuevo usuario en el sistema.
     */
    public LoginResponse registrar(RegisterRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            return LoginResponse.builder()
                    .exito(false)
                    .mensaje("El correo electrónico ya está en uso.")
                    .build();
        }

        // Crear la entidad modelo a partir de la petición
        UsuarioModel nuevoUsuario = UsuarioModel.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Encriptar clave
                .rol("ROLE_USER")
                .build();

        usuarioRepository.save(nuevoUsuario);

        return LoginResponse.builder()
                .exito(true)
                .mensaje("Usuario registrado correctamente.")
                .email(nuevoUsuario.getEmail())
                .nombre(nuevoUsuario.getNombre())
                .rol(nuevoUsuario.getRol())
                .build();
    }
}
