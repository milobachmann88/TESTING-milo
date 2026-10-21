package etec.backend.service

data class Item(
    val nombre: String,
    val precio: Int
)

class OrdenDeCompra() {
    private val items= mutableListOf<Item>()
    fun agregarItem(item: Item){
        this.items.add(item)
    }
    fun calcularTotal():Int{
        return this.items.sumOf{it.precio}
    }
}
