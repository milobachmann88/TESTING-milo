package etec.bdd.controller

import etec.bdd.dto.ClienteRequest
import etec.bdd.dto.ClienteResponse
import etec.bdd.service.ClienteService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/clientes")
class ClienteController(
    private val clienteService: ClienteService
) {

    @GetMapping
    fun listar(pageable: Pageable): ResponseEntity<Page<ClienteResponse>> {
        return ResponseEntity.ok(clienteService.listar(pageable))
    }

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Long): ResponseEntity<ClienteResponse> {
        return ResponseEntity.ok(clienteService.obtener(id))
    }

    @PostMapping
    fun crear(@Valid @RequestBody req: ClienteRequest): ResponseEntity<ClienteResponse> {
        val creado = clienteService.crear(req)
        return ResponseEntity.status(HttpStatus.CREATED).body(creado)
    }

    @PutMapping("/{id}")
    fun actualizar(
        @PathVariable id: Long,
        @Valid @RequestBody req: ClienteRequest
    ): ResponseEntity<ClienteResponse> {
        val actualizado = clienteService.actualizar(id, req)
        return ResponseEntity.ok(actualizado)
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<Void> {
        clienteService.eliminar(id)
        return ResponseEntity.noContent().build()
    }
}