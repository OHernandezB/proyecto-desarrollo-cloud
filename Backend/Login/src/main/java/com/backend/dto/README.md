# DTOs (Data Transfer Objects)

Los **DTOs** son clases que utilizamos en Spring Boot para definir la estructura exacta de los datos que queremos enviar (Request) o recibir (Response) desde la API.

Pensémoslo como una "Plantilla" o "Contrato":

1. **Respuesta (LoginResponse):** Define qué información le enviamos al frontend cuando el usuario inicia sesión (ej: `token`, `email`, `nombre`).
2. **Solicitud (LoginRequest):** Define qué información esperamos recibir del frontend cuando intenta iniciar sesión (ej: `email`, `password`).

### ¿Por qué son necesarios?

Para mantener el código limpio y seguro.
- **Control de Versión:** Si en el futuro cambiamos la estructura de la respuesta, solo modificamos el DTO, y el frontend sabrá exactamente qué esperar.
- **Validación:** Permiten validar automáticamente que el frontend nos envíe datos correctos (ej: que el email tenga formato de email).
- **Seguridad:** Evitan que expongamos objetos internos de nuestra base de datos directamente a internet.
