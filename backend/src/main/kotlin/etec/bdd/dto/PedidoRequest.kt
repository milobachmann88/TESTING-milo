package etec.bdd.dto

import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class PedidoRequest(
    @field:NotNull(message = "La fecha del pedido es obligatoria")
    val fecha: LocalDateTime,

    val fechaEntrega: LocalDateTime?,

    @field:NotNull(message = "El ID del cliente es obligatorio")
    val clienteId: Long
)