package com.example.practicatfgbd.controllers.apiRest;

import com.example.practicatfgbd.entities.TareaMonitor;
import com.example.practicatfgbd.error.ResourceNotFoundException;
import com.example.practicatfgbd.services.TareaMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tareamonitor")
public class TareaMonitorController {

    @Autowired
    private TareaMonitorService tareaMonitorService;

    @PostMapping
    public ResponseEntity<TareaMonitor> addTareaMonitor(@RequestBody TareaMonitor tareaMonitor) {
        TareaMonitor newTareaMonitor = tareaMonitorService.addTareaMonitor(tareaMonitor);
        return new ResponseEntity<>(newTareaMonitor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaMonitor> editTareaMonitor(@PathVariable Long id, @RequestBody TareaMonitor tareaMonitor) {
        Optional<TareaMonitor> tareaMonitorOptional = tareaMonitorService.findById(id);
        if (tareaMonitorOptional.isPresent()) {
            TareaMonitor existingTareaMonitor = tareaMonitorOptional.get();
            existingTareaMonitor.setNombre(tareaMonitor.getNombre());
            existingTareaMonitor.setDescripcion(tareaMonitor.getDescripcion());
            existingTareaMonitor.setMateriales(tareaMonitor.getMateriales());
            TareaMonitor updatedTareaMonitor = tareaMonitorService.editTareaMonitor(existingTareaMonitor);
            return new ResponseEntity<>(updatedTareaMonitor, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("TareaMonitor not found with id: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTareaMonitor(@PathVariable Long id) {
        if (tareaMonitorService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("TareaMonitor not found with id: " + id);
        }
        tareaMonitorService.deleteTareaMonitor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaMonitor> findById(@PathVariable Long id) {
        Optional<TareaMonitor> tareaMonitorOptional = tareaMonitorService.findById(id);
        return tareaMonitorOptional.map(tareaMonitor -> new ResponseEntity<>(tareaMonitor, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<TareaMonitor>> findAll() {
        List<TareaMonitor> tareaMonitorList = tareaMonitorService.findAll();
        return new ResponseEntity<>(tareaMonitorList, HttpStatus.OK);
    }
}