package edu.tienda.core.controllers;

import edu.tienda.core.domain.Producto;
import edu.tienda.core.services.ProductosServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/productos")

public class ProductoControllerRest {


    //Se instancia la clase ProductosServiceImpl
    private ProductosServiceImpl productosService = new ProductosServiceImpl();

    @GetMapping
    public ResponseEntity<?> getProductos() {

        //Se obtiene la lista de productos
        List<Producto> productos = productosService.getProductos();

        //Se retorna la lista de productos
        return ResponseEntity.ok(productos);
    }
}
