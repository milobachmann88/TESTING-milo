package etec.bdd.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Column
import jakarta.persistence.OneToMany
import jakarta.persistence.CascadeType
import java.math.BigDecimal

@Entity
@Table(name = "producto")
class Producto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 100)
    var nombre: String = "",

    @Column(length = 300)
    var descripcion: String? = null,

    @Column(length = 50)
    var categoria: String? = null,

    @Column(nullable = false, precision = 10, scale = 2)
    var precio: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    var stock: Int = 0,

    @Column(nullable = false)
    var activo: Boolean = true,

    @OneToMany(mappedBy = "producto", cascade = [CascadeType.ALL], orphanRemoval = true)
    var detalles: MutableList<DetallePedido> = mutableListOf()
)