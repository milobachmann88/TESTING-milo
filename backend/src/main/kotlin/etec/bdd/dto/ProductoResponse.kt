package etec.bdd.dto

import etec.bdd.entity.Producto
import tools.jackson.databind.annotation.JsonSerialize
import tools.jackson.databind.ser.std.ToStringSerializer
import java.math.BigDecimal

data class ProductoResponse(
    val id: Long,
    val nombre: String,
    val descripcion: String?,
    val categoria: String?,

    @JsonSerialize(using = ToStringSerializer::class)
    val precio: BigDecimal,

    val stock: Int,
    val activo: Boolean
) {
    companion object {
        fun fromEntity(p: Producto): ProductoResponse =
            ProductoResponse(
                id = p.id ?: 0L,
                nombre = p.nombre,
                descripcion = p.descripcion,
                categoria = p.categoria,
                precio = p.precio,
                stock = p.stock,
                activo = p.activo
            )
    }
}