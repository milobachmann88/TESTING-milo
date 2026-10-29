package etec.bdd.service

import etec.bdd.dto.PedidoRequest
import etec.bdd.dto.PedidoResponse
import etec.bdd.entity.Pedido
import etec.bdd.exception.ClienteNoEncontradoException
import etec.bdd.exception.PedidoNoEncontradoException
import etec.bdd.repository.ClienteRepository
import etec.bdd.repository.PedidoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PedidoService(
    private val pedidoRepository: PedidoRepository,
    private val clienteRepository: ClienteRepository
) {

    @Transactional(readOnly = true)
    fun listar(pageable: Pageable): Page<PedidoResponse> {
        return pedidoRepository.findAll(pageable)
            .map { PedidoResponse.fromEntity(it) }
    }

    @Transactional(readOnly = true)
    fun obtener(id: Long): PedidoResponse {
        val pedido = pedidoRepository.findById(id)
            .orElseThrow { PedidoNoEncontradoException(id) }
        return PedidoResponse.fromEntity(pedido)
    }

    @Transactional
    fun crear(req: PedidoRequest): PedidoResponse {
        // Verificamos que el cliente exista antes de crear el pedido
        val cliente = clienteRepository.findById(req.clienteId)
            .orElseThrow { ClienteNoEncontradoException(req.clienteId) }

        val nuevoPedido = Pedido(
            fecha = req.fecha,
            fechaEntrega = req.fechaEntrega,
            estado = "PENDIENTE", // Estado por defecto opcional
            cliente = cliente
        )
        val guardado = pedidoRepository.save(nuevoPedido)
        return PedidoResponse.fromEntity(guardado)
    }

    @Transactional
    fun actualizar(id: Long, req: PedidoRequest): PedidoResponse {
        val pedidoExistente = pedidoRepository.findById(id)
            .orElseThrow { PedidoNoEncontradoException(id) }

        val cliente = clienteRepository.findById(req.clienteId)
            .orElseThrow { ClienteNoEncontradoException(req.clienteId) }

        pedidoExistente.fecha = req.fecha
        pedidoExistente.fechaEntrega = req.fechaEntrega
        pedidoExistente.cliente = cliente

        val actualizado = pedidoRepository.save(pedidoExistente)
        return PedidoResponse.fromEntity(actualizado)
    }

    @Transactional
    fun eliminar(id: Long) {
        val pedido = pedidoRepository.findById(id)
            .orElseThrow { PedidoNoEncontradoException(id) }
        pedidoRepository.delete(pedido)
    }
}