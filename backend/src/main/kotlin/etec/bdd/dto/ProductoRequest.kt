package etec.bdd.dto

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal

data class ProductoRequest(
    @field:NotBlank(message = "El nombre es obligatorio")
    @field:Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    val nombre: String,

    @field:Size(max = 300, message = "La descripción no puede superar los 300 caracteres")
    val descripcion: String?,

    @field:Size(max = 50, message = "La categoría no puede superar los 50 caracteres")
    val categoria: String?,

    @field:NotNull(message = "El precio es obligatorio")
    @field:DecimalMin(value = "0.0", inclusive = true, message = "El precio debe ser mayor o igual a 0")
    val precio: BigDecimal,

    @field:NotNull(message = "El stock es obligatorio")
    @field:Min(value = 0, message = "El stock no puede ser negativo")
    val stock: Int,

    val activo: Boolean? = true
)