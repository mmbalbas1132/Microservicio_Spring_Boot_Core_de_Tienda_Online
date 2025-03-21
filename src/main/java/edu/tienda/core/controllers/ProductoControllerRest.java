package edu.tienda.core.controllers;

import edu.tienda.core.domain.Producto;
import edu.tienda.core.services.ProductosServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/productos")

public class ProductoControllerRest {


    //Se instancia la clase ProductosServiceImpl
    private ProductosServiceImpl productosService = new ProductosServiceImpl();

    //Obtener todos los productos
    @GetMapping
    public ResponseEntity<?> getProductos() {

        //Se obtiene la lista de productos
        List<Producto> productos = productosService.getProductos();

        //Se retorna la lista de productos
        return ResponseEntity.ok(productos);
    }

    //Obtener un producto por su id
    @GetMapping("/{id}")
    public ResponseEntity<?> getProducto(@PathVariable Integer id) {

        //Se obtiene el producto por su id
        Producto producto = productosService.getProductoById(id);

        //Se retorna el producto
        return ResponseEntity.ok(producto);
    }

    //Dar de alta un producto
    @PostMapping
    public ResponseEntity<?> addProducto(@RequestBody Producto producto) {

        //Se agrega el producto
        productosService.addProducto(producto);

        //Se retorna el producto
        return ResponseEntity.ok(producto);
    }

    //Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProducto(@RequestBody Producto producto) {

        //Se actualiza el producto
        productosService.updateProducto(producto);

        //Se retorna el producto
        return ResponseEntity.ok(producto);
    }

    //Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Integer id) {

        //Se elimina el producto
        productosService.deleteProducto(id);

        //Se retorna el producto
        return ResponseEntity.ok("Producto eliminado");
    }
}
