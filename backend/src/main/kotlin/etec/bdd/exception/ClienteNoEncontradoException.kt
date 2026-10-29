package etec.bdd.exception

class ClienteNoEncontradoException(id: Long) :
    RuntimeException("Cliente no encontrado con el ID: $id")