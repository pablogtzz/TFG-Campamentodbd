package com.example.practicatfgbd.controllers.apiRest;

import com.example.practicatfgbd.entities.SubGrupo;
import com.example.practicatfgbd.error.ResourceNotFoundException;
import com.example.practicatfgbd.services.SubGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subgrupos")
public class SubGrupoController {

    @Autowired
    private SubGrupoService subGrupoService;

    @PostMapping
    public ResponseEntity<SubGrupo> addSubGrupo(@RequestBody SubGrupo subGrupo) {
        SubGrupo newSubGrupo = subGrupoService.addSubGrupo(subGrupo);
        return new ResponseEntity<>(newSubGrupo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubGrupo> editSubGrupo(@PathVariable Long id, @RequestBody SubGrupo subGrupo) {
        Optional<SubGrupo> subGrupoOptional = subGrupoService.findById(id);
        if (subGrupoOptional.isPresent()) {
            SubGrupo existingSubGrupo = subGrupoOptional.get();
            existingSubGrupo.setNombre(subGrupo.getNombre());
            existingSubGrupo.setNumeroIntegrantes(subGrupo.getNumeroIntegrantes());
            SubGrupo updatedSubGrupo = subGrupoService.editSubGrupo(existingSubGrupo);
            return new ResponseEntity<>(updatedSubGrupo, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("SubGrupo not found with id: " + id);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubGrupo(@PathVariable Long id) {
        if (subGrupoService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("SubGrupo not found with id: " + id);
        }
        subGrupoService.deleteSubGrupo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubGrupo> findById(@PathVariable Long id) {
        Optional<SubGrupo> subGrupoOptional = subGrupoService.findById(id);
        return subGrupoOptional.map(subGrupo -> new ResponseEntity<>(subGrupo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<SubGrupo>> findAll() {
        List<SubGrupo> subGrupoList = subGrupoService.findAll();
        return new ResponseEntity<>(subGrupoList, HttpStatus.OK);
    }
}