package etec.bdd.repository

import etec.bdd.entity.DetallePedido
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DetallePedidoRepository : JpaRepository<DetallePedido, Long>