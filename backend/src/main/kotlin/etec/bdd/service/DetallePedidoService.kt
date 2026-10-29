package etec.bdd.service

import etec.bdd.dto.DetallePedidoRequest
import etec.bdd.dto.DetallePedidoResponse
import etec.bdd.entity.DetallePedido
import etec.bdd.exception.DetallePedidoNoEncontradoException
import etec.bdd.exception.PedidoNoEncontradoException
import etec.bdd.exception.ProductoNoEncontradoException
import etec.bdd.repository.DetallePedidoRepository
import etec.bdd.repository.PedidoRepository
import etec.bdd.repository.ProductoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DetallePedidoService(
    private val detallePedidoRepository: DetallePedidoRepository,
    private val pedidoRepository: PedidoRepository,
    private val productoRepository: ProductoRepository
) {

    @Transactional(readOnly = true)
    fun listar(pageable: Pageable): Page<DetallePedidoResponse> {
        return detallePedidoRepository.findAll(pageable)
            .map { DetallePedidoResponse.fromEntity(it) }
    }

    @Transactional(readOnly = true)
    fun obtener(id: Long): DetallePedidoResponse {
        val detalle = detallePedidoRepository.findById(id)
            .orElseThrow { DetallePedidoNoEncontradoException(id) }
        return DetallePedidoResponse.fromEntity(detalle)
    }

    @Transactional
    fun crear(req: DetallePedidoRequest): DetallePedidoResponse {
        // Validar que el pedido y el producto existan antes de asociarlos
        val pedido = pedidoRepository.findById(req.pedidoId)
            .orElseThrow { PedidoNoEncontradoException(req.pedidoId) }

        val producto = productoRepository.findById(req.productoId)
            .orElseThrow { ProductoNoEncontradoException(req.productoId) }

        val nuevoDetalle = DetallePedido(
            cantidad = req.cantidad,
            precioUnitario = req.precioUnitario,
            pedido = pedido,
            producto = producto
        )
        val guardado = detallePedidoRepository.save(nuevoDetalle)
        return DetallePedidoResponse.fromEntity(guardado)
    }

    @Transactional
    fun actualizar(id: Long, req: DetallePedidoRequest): DetallePedidoResponse {
        val detalleExistente = detallePedidoRepository.findById(id)
            .orElseThrow { DetallePedidoNoEncontradoException(id) }

        val pedido = pedidoRepository.findById(req.pedidoId)
            .orElseThrow { PedidoNoEncontradoException(req.pedidoId) }

        val producto = productoRepository.findById(req.productoId)
            .orElseThrow { ProductoNoEncontradoException(req.productoId) }

        detalleExistente.cantidad = req.cantidad
        detalleExistente.precioUnitario = req.precioUnitario
        detalleExistente.pedido = pedido
        detalleExistente.producto = producto

        val actualizado = detallePedidoRepository.save(detalleExistente)
        return DetallePedidoResponse.fromEntity(actualizado)
    }

    @Transactional
    fun eliminar(id: Long) {
        val detalle = detallePedidoRepository.findById(id)
            .orElseThrow { DetallePedidoNoEncontradoException(id) }
        detallePedidoRepository.delete(detalle)
    }
}