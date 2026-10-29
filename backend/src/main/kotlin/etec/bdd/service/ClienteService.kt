package etec.bdd.service

import etec.bdd.dto.ClienteRequest
import etec.bdd.dto.ClienteResponse
import etec.bdd.entity.Cliente
import etec.bdd.exception.ClienteNoEncontradoException
import etec.bdd.repository.ClienteRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ClienteService(
    private val clienteRepository: ClienteRepository
) {

    @Transactional(readOnly = true)
    fun listar(pageable: Pageable): Page<ClienteResponse> {
        return clienteRepository.findAll(pageable)
            .map { ClienteResponse.fromEntity(it) }
    }

    @Transactional(readOnly = true)
    fun obtener(id: Long): ClienteResponse {
        val cliente = clienteRepository.findById(id)
            .orElseThrow { ClienteNoEncontradoException(id) }
        return ClienteResponse.fromEntity(cliente)
    }

    @Transactional
    fun crear(req: ClienteRequest): ClienteResponse {
        val nuevoCliente = Cliente(
            nombre = req.nombre,
            telefono = req.telefono,
            email = req.email,
            direccion = req.direccion
        )
        val guardado = clienteRepository.save(nuevoCliente)
        return ClienteResponse.fromEntity(guardado)
    }

    @Transactional
    fun actualizar(id: Long, req: ClienteRequest): ClienteResponse {
        val clienteExistente = clienteRepository.findById(id)
            .orElseThrow { ClienteNoEncontradoException(id) }

        clienteExistente.nombre = req.nombre
        clienteExistente.telefono = req.telefono
        clienteExistente.email = req.email
        clienteExistente.direccion = req.direccion

        val actualizado = clienteRepository.save(clienteExistente)
        return ClienteResponse.fromEntity(actualizado)
    }

    @Transactional
    fun eliminar(id: Long) {
        val cliente = clienteRepository.findById(id)
            .orElseThrow { ClienteNoEncontradoException(id) }
        clienteRepository.delete(cliente)
    }
}