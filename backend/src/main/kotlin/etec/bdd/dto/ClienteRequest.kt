package etec.bdd.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class ClienteRequest(
    @field:NotBlank(message = "El nombre es obligatorio")
    @field:Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    val nombre: String,

    @field:Size(max = 30, message = "El teléfono no puede superar los 30 caracteres")
    val telefono: String?,

    @field:Email(message = "Debe ser un email válido")
    @field:Size(max = 150, message = "El email no puede superar los 150 caracteres")
    val email: String?,

    @field:Size(max = 200, message = "La dirección no puede superar los 200 caracteres")
    val direccion: String?
)