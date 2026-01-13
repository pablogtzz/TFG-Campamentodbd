package com.example.practicatfgbd.controllers.apiRest;

import com.example.practicatfgbd.entities.Kids;
import com.example.practicatfgbd.error.ResourceNotFoundException;
import com.example.practicatfgbd.services.KidsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kids")
public class KidsControllerRest {

    @Autowired
    private KidsService kidsService;

    @PostMapping
    public ResponseEntity<Kids> addKids(@RequestBody Kids kids) {
        Kids newKids = kidsService.addKids(kids);
        return new ResponseEntity<>(newKids, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kids> editKids(@PathVariable Long id, @RequestBody Kids kids) {
        Optional<Kids> kidsOptional = kidsService.findById(id);
        if (kidsOptional.isPresent()) {
            Kids existingKids = kidsOptional.get();
            existingKids.setNombre(kids.getNombre());
            existingKids.setApellidos(kids.getApellidos());
            existingKids.setTallaCamiseta(kids.getTallaCamiseta());
            existingKids.setFechaNacimiento(kids.getFechaNacimiento());
            existingKids.setNombrePadre(kids.getNombrePadre());
            existingKids.setTelefonoPadre(kids.getTelefonoPadre());
            existingKids.setNombreMadre(kids.getNombreMadre());
            existingKids.setTelefonoMadre(kids.getTelefonoMadre());
            existingKids.setGrupo(kids.getGrupo());
            existingKids.setSubgrupo(kids.getSubgrupo());
            existingKids.setAlergias(kids.getAlergias());
            Kids updatedKids = kidsService.editKids(existingKids);
            return new ResponseEntity<>(updatedKids, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("Kids not found with id: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKids(@PathVariable Long id) {
        if (kidsService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Kids not found with id: " + id);
        }
        kidsService.deleteKids(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kids> findById(@PathVariable Long id) {
        Optional<Kids> kidsOptional = kidsService.findById(id);
        return kidsOptional.map(kids -> new ResponseEntity<>(kids, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Kids>> findAll() {
        List<Kids> kidsList = kidsService.findAll();
        return new ResponseEntity<>(kidsList, HttpStatus.OK);
    }
}