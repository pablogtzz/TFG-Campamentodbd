package com.example.practicatfgbd.controllers.apiRest;

import com.example.practicatfgbd.entities.Material;
import com.example.practicatfgbd.error.ResourceNotFoundException;
import com.example.practicatfgbd.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {

    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping
    public ResponseEntity<Material> añadirMaterial(@RequestBody Material material) {
        Material nuevoMaterial = materialService.addMaterial(material);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMaterial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Material> editarMaterial(@PathVariable Long id, @RequestBody Material material) {
        if (materialService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Material not found with id: " + id);
        }
        material.setIdMaterial(id);
        Material materialActualizado = materialService.editMaterial(material);
        return ResponseEntity.ok(materialActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMaterial(@PathVariable Long id) {
        if (materialService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Material not found with id: " + id);
        }
        materialService.deleteMaterial(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> buscarPorId(@PathVariable Long id) {
        Optional<Material> materialOptional = materialService.findById(id);
        return materialOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Material>> buscarTodos() {
        List<Material> materiales = materialService.findAll();
        return ResponseEntity.ok(materiales);
    }

}
