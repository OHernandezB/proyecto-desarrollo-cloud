package com.backend.controller;

import com.backend.dto.LoginRequest;
import com.backend.dto.LoginResponse;
import com.backend.dto.RegisterRequest;
import com.backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ==========================================
 * CAPA DE CONTROLADOR (CONTROLLER - MVC)
 * ==========================================
 * El Controlador gestiona la recepción de solicitudes HTTP (GET, POST, PUT, DELETE),
 * delega la lógica de negocio a la capa de Servicio, y retorna las respuestas en formato JSON.
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Permite la conexión desde cualquier frontend (React, Vue, HTML, etc.)
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint para iniciar sesión: POST /api/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse respuesta = authService.login(request);

        if (!respuesta.isExito()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta);
        }

        return ResponseEntity.ok(respuesta);
    }

    /**
     * Endpoint para registrar un usuario: POST /api/auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> registrar(@Valid @RequestBody RegisterRequest request) {
        LoginResponse respuesta = authService.registrar(request);

        if (!respuesta.isExito()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
}
