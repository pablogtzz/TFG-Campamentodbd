package com.example.practicatfgbd.controllers.apiRest;

import com.example.practicatfgbd.entities.Juegos;
import com.example.practicatfgbd.error.ResourceNotFoundException;
import com.example.practicatfgbd.services.JuegosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/juegos")
public class JuegosControllerRest {

    private final JuegosService juegosService;

    @Autowired
    public JuegosControllerRest(JuegosService juegosService) {
        this.juegosService = juegosService;
    }

    @PostMapping
    public ResponseEntity<Juegos> añadirJuego(@RequestBody Juegos juego) {
        Juegos nuevoJuego = juegosService.save(juego);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoJuego);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Juegos> editarJuego(@PathVariable Long id, @RequestBody Juegos juego) {
        if (juegosService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Juego not found with id: " + id);
        }
        juego.setIdJuegos(id);
        Juegos juegoActualizado = juegosService.editJuegos(juego);
        return ResponseEntity.ok(juegoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarJuego(@PathVariable Long id) {
        if (juegosService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Juego not found with id: " + id);
        }
        juegosService.deleteJuegos(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Juegos> buscarPorId(@PathVariable Long id) {
        Optional<Juegos> juegoOptional = juegosService.findById(id);
        return juegoOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Juegos>> buscarTodos() {
        List<Juegos> juegos = juegosService.findAll();
        return ResponseEntity.ok(juegos);
    }

}