package com.example.practicatfgbd.controllers.apiRest;

import com.example.practicatfgbd.entities.TareaKids;
import com.example.practicatfgbd.error.ResourceNotFoundException;
import com.example.practicatfgbd.services.TareaKidsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tareakids")
public class TareaKidsController {

    @Autowired
    private TareaKidsService tareaKidsService;

    @PostMapping
    public ResponseEntity<TareaKids> addTareaKids(@RequestBody TareaKids tareaKids) {
        TareaKids newTareaKids = tareaKidsService.addTareaKids(tareaKids);
        return new ResponseEntity<>(newTareaKids, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaKids> editTareaKids(@PathVariable Long id, @RequestBody TareaKids tareaKids) {
        Optional<TareaKids> tareaKidsOptional = tareaKidsService.findById(id);
        if (tareaKidsOptional.isPresent()) {
            TareaKids existingTareaKids = tareaKidsOptional.get();
            existingTareaKids.setNombre(tareaKids.getNombre());
            existingTareaKids.setDescripcion(tareaKids.getDescripcion());
            existingTareaKids.setMateriales(tareaKids.getMateriales());
            TareaKids updatedTareaKids = tareaKidsService.editTareaKids(existingTareaKids);
            return new ResponseEntity<>(updatedTareaKids, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("TareaKids not found with id: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTareaKids(@PathVariable Long id) {
        if (tareaKidsService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("TareaKids not found with id: " + id);
        }
        tareaKidsService.deleteTareaKids(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaKids> findById(@PathVariable Long id) {
        Optional<TareaKids> tareaKidsOptional = tareaKidsService.findById(id);
        return tareaKidsOptional.map(tareaKids -> new ResponseEntity<>(tareaKids, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<TareaKids>> findAll() {
        List<TareaKids> tareaKidsList = tareaKidsService.findAll();
        return new ResponseEntity<>(tareaKidsList, HttpStatus.OK);
    }
}