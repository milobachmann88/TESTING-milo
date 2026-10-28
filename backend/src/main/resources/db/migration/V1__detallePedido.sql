CREATE TABLE IF NOT EXIST clientes(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cantidad INTEGER NOT NULL DEFAULT 1,
    precio_unitario FLOAT NOT NULL,
    pedido_id INTEGER NOT NULL,
    producto_id INTEGER NOT NULL,
    CONSTRAINT fk_detalle_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id),
    CONSTRAINT fk_detalle_producto FOREIGN KEY (producto_id) REFERENCES producto(id)
    )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;