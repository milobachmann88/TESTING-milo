package etec.bdd.dto

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class DetallePedidoRequest(
    @field:NotNull(message = "La cantidad es obligatoria")
    @field:Min(value = 1, message = "La cantidad mínima debe ser 1")
    val cantidad: Int,

    @field:NotNull(message = "El precio unitario es obligatorio")
    @field:DecimalMin(value = "0.0", inclusive = false, message = "El precio unitario debe ser mayor a 0")
    val precioUnitario: BigDecimal,

    @field:NotNull(message = "El ID del pedido es obligatorio")
    val pedidoId: Long,

    @field:NotNull(message = "El ID del producto es obligatorio")
    val productoId: Long
)