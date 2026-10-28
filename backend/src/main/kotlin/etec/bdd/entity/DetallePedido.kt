package etec.bdd.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Column
import jakarta.persistence.ManyToOne
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import java.math.BigDecimal

@Entity
@Table(name = "detallePedido")
class DetallePedido(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var cantidad: Int = 1,

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    var precioUnitario: BigDecimal = BigDecimal.ZERO,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    var pedido: Pedido? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    var producto: Producto? = null
)