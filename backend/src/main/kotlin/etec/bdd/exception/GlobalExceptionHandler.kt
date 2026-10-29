package etec.bdd.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import tools.jackson.core.JacksonException
import java.time.LocalDateTime

data class ErrorResponse(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String,
)

@RestControllerAdvice
class GlobalExceptionHandler {

    // Manejador para Cliente No Encontrado (404)
    @ExceptionHandler(ClienteNoEncontradoException::class)
    fun handleClienteNoEncontrado(ex: ClienteNoEncontradoException): ResponseEntity<ErrorResponse> {
        val body = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = "Not Found",
            message = ex.message ?: "Cliente no encontrado"
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body)
    }

    // Manejador para Producto No Encontrado (404)
    @ExceptionHandler(ProductoNoEncontradoException::class)
    fun handleProductoNoEncontrado(ex: ProductoNoEncontradoException): ResponseEntity<ErrorResponse> {
        val body = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = "Not Found",
            message = ex.message ?: "Producto no encontrado"
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body)
    }

    // Manejador para Pedido No Encontrado (404)
    @ExceptionHandler(PedidoNoEncontradoException::class)
    fun handlePedidoNoEncontrado(ex: PedidoNoEncontradoException): ResponseEntity<ErrorResponse> {
        val body = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = "Not Found",
            message = ex.message ?: "Pedido no encontrado"
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body)
    }

    // Manejador para Detalle de Pedido No Encontrado (404)
    @ExceptionHandler(DetallePedidoNoEncontradoException::class)
    fun handleDetallePedidoNoEncontrado(ex: DetallePedidoNoEncontradoException): ResponseEntity<ErrorResponse> {
        val body = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = "Not Found",
            message = ex.message ?: "Detalle de pedido no encontrado"
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body)
    }

    // Manejador de errores de validación de los Request DTOs (400)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val detalle = ex.bindingResult.fieldErrors
            .joinToString(", ") { "${it.field}:${it.defaultMessage}" }

        val body = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Bad Request",
            message = "Error de validación: $detalle"
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body)
    }

    // Manejador para errores de sintaxis en el JSON (400)
    @ExceptionHandler(JacksonException::class)
    fun handleJsonException(ex: JacksonException): ResponseEntity<ErrorResponse> {
        val body = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Bad Request",
            message = "JSON malformado o inválido: ${ex.message}"
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body)
    }
}