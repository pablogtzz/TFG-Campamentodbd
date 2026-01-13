package com.example.practicatfgbd.controllers.apiRest;

import com.example.practicatfgbd.entities.Alergias;
import com.example.practicatfgbd.error.ResourceNotFoundException;
import com.example.practicatfgbd.services.AlergiasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alergias")
public class AlergiasController {

    @Autowired
    private AlergiasService alergiasService;

    @PostMapping
    public ResponseEntity<Alergias> addAlergias(@RequestBody Alergias alergias) {
        Alergias newAlergias = alergiasService.addAlergias(alergias);
        return new ResponseEntity<>(newAlergias, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alergias> editAlergias(@PathVariable Long id, @RequestBody Alergias alergias) {
        Optional<Alergias> alergiasOptional = alergiasService.findById(id);
        if (alergiasOptional.isPresent()) {
            Alergias existingAlergias = alergiasOptional.get();
            existingAlergias.setDescripcion(alergias.getDescripcion());
            Alergias updatedAlergias = alergiasService.editAlergias(existingAlergias);
            return new ResponseEntity<>(updatedAlergias, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("Alergia not found with id: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlergias(@PathVariable Long id) {
        if (alergiasService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Alergia not found with id: " + id);
        }
        alergiasService.deleteAlergias(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alergias> findById(@PathVariable Long id) {
        Optional<Alergias> alergiasOptional = alergiasService.findById(id);
        return alergiasOptional.map(alergias -> new ResponseEntity<>(alergias, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Alergias>> findAll() {
        List<Alergias> alergiasList = alergiasService.findAll();
        return new ResponseEntity<>(alergiasList, HttpStatus.OK);
    }
}
