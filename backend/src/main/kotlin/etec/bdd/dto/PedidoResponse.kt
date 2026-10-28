package etec.bdd.dto

import etec.bdd.entity.Pedido
import java.time.LocalDateTime

data class PedidoResponse(
    val id: Long,
    val fecha: LocalDateTime,
    val fechaEntrega: LocalDateTime?,
    val estado: String?,
    val clienteId: Long,
    val clienteNombre: String?
) {
    companion object {
        fun fromEntity(p: Pedido): PedidoResponse =
            PedidoResponse(
                id = p.id ?: 0L,
                fecha = p.fecha,
                fechaEntrega = p.fechaEntrega,
                estado = p.estado,
                clienteId = p.cliente?.id ?: 0L,
                clienteNombre = p.cliente?.nombre
            )
    }
}