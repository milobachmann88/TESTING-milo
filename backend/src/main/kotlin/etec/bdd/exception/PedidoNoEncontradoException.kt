package etec.bdd.exception

class PedidoNoEncontradoException(id: Long) :
    RuntimeException("Pedido no encontrado con el ID: $id")