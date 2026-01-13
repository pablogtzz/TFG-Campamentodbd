package com.example.practicatfgbd.controllers.apiRest;

import com.example.practicatfgbd.entities.Campamento;

import com.example.practicatfgbd.error.ResourceNotFoundException;
import com.example.practicatfgbd.services.CampamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/campamentos")
public class CampamentoControllerRest {

    @Autowired
    private CampamentoService campamentoService;

    @PostMapping
    public ResponseEntity<Campamento> añadirCampamento(@RequestBody Campamento campamento) {
        Campamento nuevoCampamento = campamentoService.addCamapamento(campamento);
        return new ResponseEntity<>(nuevoCampamento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campamento> editarCampamento(@PathVariable Long id, @RequestBody Campamento campamento) {
        Optional<Campamento> campamentoOptional = campamentoService.findById(id);
        if (campamentoOptional.isPresent()) {
            Campamento campamentoExistente = campamentoOptional.get();
            campamentoExistente.setNombre(campamento.getNombre());
            campamentoExistente.setCapacidad(campamento.getCapacidad());
            campamentoExistente.setPrecio(campamento.getPrecio());
            campamentoExistente.setLugar(campamento.getLugar());
            campamentoExistente.setFechas(campamento.getFechas());
            Campamento campamentoActualizado = campamentoService.editCampamento(campamentoExistente);
            return new ResponseEntity<>(campamentoActualizado, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("Campamento not found with id: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCampamento(@PathVariable Long id) {
        if (campamentoService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Campamento not found with id: " + id);
        }
        campamentoService.deleteCamapento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campamento> buscarPorId(@PathVariable Long id) {
        Optional<Campamento> campamentoOptional = campamentoService.findById(id);
        return campamentoOptional.map(campamento -> new ResponseEntity<>(campamento, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Campamento>> obtenerTodosLosCampamentos() {
        List<Campamento> campamentos = campamentoService.findAll();
        return new ResponseEntity<>(campamentos, HttpStatus.OK);
    }
}
