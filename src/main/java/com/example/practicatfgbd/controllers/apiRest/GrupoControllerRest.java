package com.example.practicatfgbd.controllers.apiRest;

import com.example.practicatfgbd.entities.Grupo;
import com.example.practicatfgbd.error.ResourceNotFoundException;
import com.example.practicatfgbd.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grupos")
public class GrupoControllerRest {

    @Autowired
    private GrupoService grupoService;

    @PostMapping
    public ResponseEntity<Grupo> addGrupo(@RequestBody Grupo grupo) {
        Grupo newGrupo = grupoService.addGrupo(grupo);
        return new ResponseEntity<>(newGrupo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grupo> editGrupo(@PathVariable Long id, @RequestBody Grupo grupo) {
        Optional<Grupo> grupoOptional = grupoService.findById(id);
        if (grupoOptional.isPresent()) {
            Grupo existingGrupo = grupoOptional.get();
            existingGrupo.setNombre(grupo.getNombre());
            existingGrupo.setEdades(grupo.getEdades());
            Grupo updatedGrupo = grupoService.editGrupo(existingGrupo);
            return new ResponseEntity<>(updatedGrupo, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("Grupo not found with id: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrupo(@PathVariable Long id) {
        if (grupoService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Alergia not found with id: " + id);
        }
        grupoService.deleteGrupo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grupo> findById(@PathVariable Long id) {
        Optional<Grupo> grupoOptional = grupoService.findById(id);
        return grupoOptional.map(grupo -> new ResponseEntity<>(grupo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Grupo>> findAll() {
        List<Grupo> grupoList = grupoService.findAll();
        return new ResponseEntity<>(grupoList, HttpStatus.OK);
    }
}
