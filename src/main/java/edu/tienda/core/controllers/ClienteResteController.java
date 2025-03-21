package edu.tienda.core.controllers;


import edu.tienda.core.domain.Cliente;
import edu.tienda.core.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
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
    public ResponseEntity<?> getClientes() {
        return ResponseEntity.ok(clientes);
    }

    //Obtener un cliente por su username
//    @GetMapping("/{username}")
//    public ResponseEntity <?>  getCliente(@PathVariable String username) {
//        for (Cliente cliente : clientes) {
//            if (cliente.getUsername().equalsIgnoreCase(username)) {
//                return ResponseEntity.ok(cliente);
//            }
//        }
//        throw new ResourceNotFoundException("Cliente no encontrado");
//    }

    //Recodifico la oanterior como un stream y una expresión lambda
    //En primer lugar se convierte la lista de clientes a tipo “stream” dándonos la posibilidad de ejecutar algunos métodos útiles. En este caso utilizamos “filter” que es el que necesitamos para realizar un filtrado por “userName”.  En segundo término se ejecuta el método “findFirst()” que retornará un opcional de cliente. El método “map” transformara el “Optional<Cliente>” en un “Optional<ResponseEntity<Cliente>>” y retornará este objeto directamente. Esto equivale a la siguiente codificación: “return ResponseEntity.ok(cliente)”. En el caso de que el opcional de cliente no contenga un objeto, se ejecutará la última línea que provocará la excepción. Luego será Spring el encargado de transformar la respuesta del servicio como NOT FOUND.


    @GetMapping("/{username}")
    public ResponseEntity<Cliente> getCliente(@PathVariable String username) {
        return clientes.stream()
                .filter(cliente -> cliente.getUsername().equals(username))
                .findFirst().map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente " + username + " no encontrado"));
    }


    //Obtener un cliente por su nombre
//    @GetMapping("/nombre/{nombre}")
//    public Cliente getClientePorNombre(@PathVariable String nombre) {
//        for (Cliente cliente : clientes) {
//            if (cliente.getNombre().equalsIgnoreCase(nombre)) {
//                return cliente;
//            }
//        }
//        return null;
//    }

    //Obtener un cliente por su nombre con stream y expresión lambda
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> getClientePorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(clientes.stream()
                .filter(cliente -> cliente.getNombre().equalsIgnoreCase(nombre))
                .findFirst().orElseThrow());

    }

    //Dar de alta un cliente
    //A diferencia de los anteriores servicios de recupero, este endpoint de alta lógicamente necesitará la información del usuario a crear.
    // Esta información es proveída por el consumidor del servicio en formato JSON, y es Spring Boot, quien se encargará del trabajo de convertir ese JSON
    //en un objeto de tipo Cliente. Para ello, se utiliza la anotación @RequestBody en el parámetro del método.
    //Para consumir este servicio, se debe enviar una petición POST a la URL /clientes con el siguiente JSON en el cuerpo de la petición:
    //Para resover esto uso Postman
    @PostMapping
    public ResponseEntity<?> altaCliente(@RequestBody Cliente cliente) {
        clientes.add(cliente);

        //Obtener url de la petición
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(cliente.getUsername())
                .toUri();
        return ResponseEntity.created(location).body(cliente);
    }

    //Modificar un cliente
    //Para modificar un cliente, se debe enviar una petición PUT a la URL /clientes/{username} con el siguiente JSON en el cuerpo de la petición:
    //Para resover esto uso Postman
    @PutMapping
    public ResponseEntity<?> modificarCliente(@RequestBody Cliente cliente) {
        Cliente clienteEncontrado = clientes.stream()
                .filter(c -> c.getUsername().equalsIgnoreCase(cliente.getUsername()))
                .findFirst()
                .orElseThrow();
        clienteEncontrado.setNombre(cliente.getNombre());
        clienteEncontrado.setPassword(cliente.getPassword());
        return ResponseEntity.ok(clienteEncontrado);
    }

    //Eliminar un cliente
    //Para eliminar un cliente, se debe enviar una petición DELETE a la URL /clientes/{username}
    @DeleteMapping("{username}")
    public ResponseEntity eliminarCliente(@PathVariable String username) {
        Cliente clienteEncontrado = clientes.stream()
                .filter(c -> c.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow();
        clientes.remove(clienteEncontrado);
        return ResponseEntity.noContent().build();
    }

}
