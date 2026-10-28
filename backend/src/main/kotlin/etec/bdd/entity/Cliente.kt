package etec.bdd.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.OneToMany
import jakarta.persistence.CascadeType

@Entity
@Table(name = "cliente")
class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 100)
    var nombre: String = "",

    @Column(length = 30)
    var telefono: String? = null,

    @Column(length = 150)
    var email: String? = null,

    @Column(length = 200)
    var direccion: String? = null,

    @OneToMany(mappedBy = "cliente", cascade = [CascadeType.ALL], orphanRemoval = true)
    var pedidos: MutableList<Pedido> = mutableListOf()
)