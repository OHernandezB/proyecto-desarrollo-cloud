package com.backend.controller;

import com.backend.dto.LoginResponse;
import com.backend.dto.MicrosoftLoginRequest;
import com.backend.service.MicrosoftAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ==========================================
 * CONTROLADOR REST - MICROSOFT AUTH (MSAL)
 * ==========================================
 * Endpoint expuesto para recibir tokens/datos provenientes de MSAL.js.
 */
@RestController
@RequestMapping("/api/auth/microsoft")
@CrossOrigin(origins = "*")
public class MicrosoftAuthController {

    private final MicrosoftAuthService microsoftAuthService;

    @Autowired
    public MicrosoftAuthController(MicrosoftAuthService microsoftAuthService) {
        this.microsoftAuthService = microsoftAuthService;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> autenticar(@Valid @RequestBody MicrosoftLoginRequest request) {
        LoginResponse respuesta = microsoftAuthService.autenticarConMicrosoft(request);
        if (!respuesta.isExito()) {
            return ResponseEntity.badRequest().body(respuesta);
        }
        return ResponseEntity.ok(respuesta);
    }
}
