package etec.bdd.controller

import etec.bdd.dto.PedidoRequest
import etec.bdd.dto.PedidoResponse
import etec.bdd.service.PedidoService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pedidos")
class PedidoController(
    private val pedidoService: PedidoService
) {

    @GetMapping
    fun listar(pageable: Pageable): ResponseEntity<Page<PedidoResponse>> {
        return ResponseEntity.ok(pedidoService.listar(pageable))
    }

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Long): ResponseEntity<PedidoResponse> {
        return ResponseEntity.ok(pedidoService.obtener(id))
    }

    @PostMapping
    fun crear(@Valid @RequestBody req: PedidoRequest): ResponseEntity<PedidoResponse> {
        val creado = pedidoService.crear(req)
        return ResponseEntity.status(HttpStatus.CREATED).body(creado)
    }

    @PutMapping("/{id}")
    fun actualizar(
        @PathVariable id: Long,
        @Valid @RequestBody req: PedidoRequest
    ): ResponseEntity<PedidoResponse> {
        val actualizado = pedidoService.actualizar(id, req)
        return ResponseEntity.ok(actualizado)
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<Void> {
        pedidoService.eliminar(id)
        return ResponseEntity.noContent().build()
    }
}