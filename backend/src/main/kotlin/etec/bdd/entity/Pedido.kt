package etec.bdd.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Column
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.CascadeType
import java.time.LocalDateTime

@Entity
@Table(name = "pedido")
class Pedido(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var fecha: LocalDateTime = LocalDateTime.now(),

    @Column(name = "fecha_entrega")
    var fechaEntrega: LocalDateTime? = null,

    @Column(length = 50)
    var estado: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    var cliente: Cliente? = null,

    @OneToMany(mappedBy = "pedido", cascade = [CascadeType.ALL], orphanRemoval = true)
    var detalles: MutableList<DetallePedido> = mutableListOf()
)
