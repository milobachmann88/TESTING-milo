package etec.bdd.dto

import etec.bdd.entity.Cliente

data class ClienteResponse(
    val id: Long,
    val nombre: String,
    val telefono: String?,
    val email: String?,
    val direccion: String?
) {
    companion object {
        fun fromEntity(c: Cliente): ClienteResponse =
            ClienteResponse(
                id = c.id ?: 0L,
                nombre = c.nombre,
                telefono = c.telefono,
                email = c.email,
                direccion = c.direccion
            )
    }
}