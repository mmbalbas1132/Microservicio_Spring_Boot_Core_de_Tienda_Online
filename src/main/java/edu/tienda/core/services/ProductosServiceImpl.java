package edu.tienda.core.services;

import edu.tienda.core.domain.Producto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductosServiceImpl {

    private List<Producto> productos = new ArrayList<>(Arrays.asList(
            new Producto(1, "Producto 1", 100.0, 10),
            new Producto(2, "Producto 2", 200.0, 20),
            new Producto(3, "Producto 3", 300.0, 30)
    ));

    // Implementar los siguientes métodos


    // Método que retorna la lista de productos
    public List<Producto> getProductos() {
        return productos;
    }

    // Método que retorna un producto por su id
    public Producto getProductoById(@PathVariable Integer id) {
        return productos.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    // Método que agrega un producto a la lista
    public void addProducto(Producto producto) {
        productos.add(producto);
    }

    // Método que actualiza un producto de la lista
    public void updateProducto(Producto producto) {
        Producto p = getProductoById(producto.getId());
        if (p != null) {
            p.setNombre(producto.getNombre());
            p.setPrecio(producto.getPrecio());
            p.setStock(producto.getStock());
        }
    }

    // Método que elimina un producto de la lista
    public void deleteProducto(Integer id) {
        productos.removeIf(p -> p.getId().equals(id));
    }

}
