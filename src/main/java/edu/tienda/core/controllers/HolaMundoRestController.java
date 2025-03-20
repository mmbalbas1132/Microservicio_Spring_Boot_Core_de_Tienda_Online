package edu.tienda.core.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HolaMundoRestController {
   @GetMapping("/hola/{nombre}")
    public String saludo(@PathVariable String nombre) {
       System.out.println("Hola Mundo desde el RestController");
         return "Hola " + nombre;
    }
}
