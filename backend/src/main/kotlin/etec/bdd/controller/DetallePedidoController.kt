package etec.bdd.controller

import etec.bdd.dto.DetallePedidoRequest
import etec.bdd.dto.DetallePedidoResponse
import etec.bdd.service.DetallePedidoService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/detalles-pedidos")
class DetallePedidoController(
    private val detallePedidoService: DetallePedidoService
) {

    @GetMapping
    fun listar(pageable: Pageable): ResponseEntity<Page<DetallePedidoResponse>> {
        return ResponseEntity.ok(detallePedidoService.listar(pageable))
    }

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Long): ResponseEntity<DetallePedidoResponse> {
        return ResponseEntity.ok(detallePedidoService.obtener(id))
    }

    @PostMapping
    fun crear(@Valid @RequestBody req: DetallePedidoRequest): ResponseEntity<DetallePedidoResponse> {
        val creado = detallePedidoService.crear(req)
        return ResponseEntity.status(HttpStatus.CREATED).body(creado)
    }

    @PutMapping("/{id}")
    fun actualizar(
        @PathVariable id: Long,
        @Valid @RequestBody req: DetallePedidoRequest
    ): ResponseEntity<DetallePedidoResponse> {
        val actualizado = detallePedidoService.actualizar(id, req)
        return ResponseEntity.ok(actualizado)
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<Void> {
        detallePedidoService.eliminar(id)
        return ResponseEntity.noContent().build()
    }
}