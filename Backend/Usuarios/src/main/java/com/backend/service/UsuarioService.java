package com.backend.service;

import com.backend.dto.UsuarioRequest;
import com.backend.dto.UsuarioResponse;
import com.backend.model.UsuarioModel;
import com.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ==========================================
 * CAPA DE SERVICIO (LÓGICA DE NEGOCIO)
 * ==========================================
 */
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponse> obtenerTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioResponse> obtenerPorId(Long id) {
        return usuarioRepository.findById(id).map(this::mapToResponse);
    }

    public UsuarioResponse crear(UsuarioRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        UsuarioModel nuevoUsuario = UsuarioModel.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(request.getRol() != null ? request.getRol() : "ROLE_USER")
                .build();

        UsuarioModel guardado = usuarioRepository.save(nuevoUsuario);
        return mapToResponse(guardado);
    }

    public Optional<UsuarioResponse> actualizar(Long id, UsuarioRequest request) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(request.getNombre());
            usuario.setEmail(request.getEmail());
            if (request.getPassword() != null && !request.getPassword().isBlank()) {
                usuario.setPassword(passwordEncoder.encode(request.getPassword()));
            }
            if (request.getRol() != null) {
                usuario.setRol(request.getRol());
            }
            UsuarioModel actualizado = usuarioRepository.save(usuario);
            return mapToResponse(actualizado);
        });
    }

    public boolean eliminar(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private UsuarioResponse mapToResponse(UsuarioModel usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .rol(usuario.getRol())
                .build();
    }
}
