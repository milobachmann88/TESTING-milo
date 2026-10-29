package etec.bdd.service

import etec.bdd.dto.ProductoRequest
import etec.bdd.dto.ProductoResponse
import etec.bdd.entity.Producto
import etec.bdd.exception.ProductoNoEncontradoException
import etec.bdd.repository.ProductoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductoService(
    private val productoRepository: ProductoRepository
) {

    @Transactional(readOnly = true)
    fun listar(pageable: Pageable): Page<ProductoResponse> {
        return productoRepository.findAll(pageable)
            .map { ProductoResponse.fromEntity(it) }
    }

    @Transactional(readOnly = true)
    fun obtener(id: Long): ProductoResponse {
        val producto = productoRepository.findById(id)
            .orElseThrow { ProductoNoEncontradoException(id) }
        return ProductoResponse.fromEntity(producto)
    }

    @Transactional
    fun crear(req: ProductoRequest): ProductoResponse {
        val nuevoProducto = Producto(
            nombre = req.nombre,
            descripcion = req.descripcion,
            categoria = req.categoria,
            precio = req.precio,
            stock = req.stock,
            activo = req.activo ?: true
        )
        val guardado = productoRepository.save(nuevoProducto)
        return ProductoResponse.fromEntity(guardado)
    }

    @Transactional
    fun actualizar(id: Long, req: ProductoRequest): ProductoResponse {
        val productoExistente = productoRepository.findById(id)
            .orElseThrow { ProductoNoEncontradoException(id) }

        productoExistente.nombre = req.nombre
        productoExistente.descripcion = req.descripcion
        productoExistente.categoria = req.categoria
        productoExistente.precio = req.precio
        productoExistente.stock = req.stock
        productoExistente.activo = req.activo ?: productoExistente.activo

        val actualizado = productoRepository.save(productoExistente)
        return ProductoResponse.fromEntity(actualizado)
    }

    @Transactional
    fun eliminar(id: Long) {
        val producto = productoRepository.findById(id)
            .orElseThrow { ProductoNoEncontradoException(id) }
        productoRepository.delete(producto)
    }
}