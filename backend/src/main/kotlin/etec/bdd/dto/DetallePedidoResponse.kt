package etec.bdd.dto

import etec.bdd.entity.DetallePedido
import tools.jackson.databind.annotation.JsonSerialize
import tools.jackson.databind.ser.std.ToStringSerializer
import java.math.BigDecimal

data class DetallePedidoResponse(
    val id: Long,
    val cantidad: Int,

    @JsonSerialize(using = ToStringSerializer::class)
    val precioUnitario: BigDecimal,

    val pedidoId: Long,
    val productoId: Long,
    val productoNombre: String?
) {
    companion object {
        fun fromEntity(d: DetallePedido): DetallePedidoResponse =
            DetallePedidoResponse(
                id = d.id ?: 0L,
                cantidad = d.cantidad,
                precioUnitario = d.precioUnitario,
                pedidoId = d.pedido?.id ?: 0L,
                productoId = d.producto?.id ?: 0L,
                productoNombre = d.producto?.nombre
            )
    }
}