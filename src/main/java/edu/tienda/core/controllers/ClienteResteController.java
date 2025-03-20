package edu.tienda.core.controllers;


import edu.tienda.core.domain.Cliente;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientes")

public class ClienteResteController {

    private List<Cliente> clientes;

    //Constructor lista de clientes con datos de prueba simulando una base de datos usando List.of
    public ClienteResteController() {
        clientes = new ArrayList<>(List.of(
                new Cliente("mbn", "1234", "Manuel"),
                new Cliente("ngl", "1234", "Noelia"),
                new Cliente("abg", "1234", "Aitana")
        ));
    }


    //Obtener todos los clientes
    @GetMapping
    public List<Cliente> getClientes() {
        return clientes;
    }

    //Obtener un cliente por su username
//    @GetMapping("/clientes/{username}")
//    public Cliente getCliente(@PathVariable String username) {
//        for (Cliente cliente : clientes) {
//            if (cliente.getUsername().equalsIgnoreCase(username)) {
//                return cliente;
//            }
//        }
//        return null;
//    }

    //    Recodifico la oanterior como un stream y una expresión lambda
    @GetMapping("/{username}")
    public Cliente getCliente(@PathVariable String username) {
        return clientes.stream()
                .filter(cliente -> cliente.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow();
    }


    //Obtener un cliente por su nombre
    @GetMapping("/nombre/{nombre}")
    public Cliente getClientePorNombre(@PathVariable String nombre) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equalsIgnoreCase(nombre)) {
                return cliente;
            }
        }
        return null;
    }

    //Obtener un cliente por su nombre con stream y expresión lambda
//    @GetMapping("/clientes/nombre/{nombre}")
//    public Cliente getClientePorNombre(@PathVariable String nombre) {
//        return clientes.stream()
//                .filter(cliente -> cliente.getNombre().equalsIgnoreCase(nombre))
//                .findFirst().orElseThrow();

//    }

    //Dar de alta un cliente
    //A diferencia de los anteriores servicios de recupero, este endpoint de alta lógicamente necesitará la información del usuario a crear.
    // Esta información es proveída por el consumidor del servicio en formato JSON, y es Spring Boot, quien se encargará del trabajo de convertir ese JSON
    //en un objeto de tipo Cliente. Para ello, se utiliza la anotación @RequestBody en el parámetro del método.
    //Para consumir este servicio, se debe enviar una petición POST a la URL /clientes con el siguiente JSON en el cuerpo de la petición:
    //Para resover esto uso Postman
    @PostMapping
    public Cliente altaCliente(@RequestBody Cliente cliente) {
        clientes.add(cliente);
        return cliente;
    }

    //Modificar un cliente
    //Para modificar un cliente, se debe enviar una petición PUT a la URL /clientes/{username} con el siguiente JSON en el cuerpo de la petición:
    //Para resover esto uso Postman
    @PutMapping
    public Cliente modificarCliente(@RequestBody Cliente cliente) {
       Cliente clienteEncontrado = clientes.stream()
                .filter(c -> c.getUsername().equalsIgnoreCase(cliente.getUsername()))
                .findFirst()
                .orElseThrow();
        clienteEncontrado.setNombre(cliente.getNombre());
        clienteEncontrado.setPassword(cliente.getPassword());
        return clienteEncontrado;
    }

    //Eliminar un cliente
    //Para eliminar un cliente, se debe enviar una petición DELETE a la URL /clientes/{username}
    @DeleteMapping("{username}")
    public void eliminarCliente(@PathVariable String username) {
        Cliente clienteEncontrado = clientes.stream()
                .filter(c -> c.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow();
        clientes.remove(clienteEncontrado);
    }

}
