package etec.bdd.exception

class DetallePedidoNoEncontradoException(id: Long) :
    RuntimeException("Detalle de pedido no encontrado con el ID: $id")