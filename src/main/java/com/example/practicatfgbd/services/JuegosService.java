package com.example.practicatfgbd.services;

import com.example.practicatfgbd.entities.Juegos;
import com.example.practicatfgbd.entities.Material;
import com.example.practicatfgbd.repositories.JuegosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JuegosService {

    @Autowired
    private JuegosRepository juegosRepository;

    @Autowired
    private MaterialService materialService;

    public Juegos save(Juegos juegos) {
        /*for (Material material : juegos.getMateriales()) {
            Optional<Material> existingMaterial = materialService.findByNombre(material.getNombre());
            if (existingMaterial.isEmpty()) {
                materialService.addMaterial(material);
            } else {
                material.setIdMaterial(existingMaterial.get().getIdMaterial());
            }
        }*/
        return juegosRepository.save(juegos);
    }

    public Optional<Juegos> findById(Long id) {
        return juegosRepository.findById(id);
    }

    public List<Juegos> findAll() {
        return juegosRepository.findAll();
    }

    public Juegos editJuegos(Juegos juego) {
        return juegosRepository.save(juego);
    }

    public void deleteJuegos(Long id) {
        juegosRepository.deleteById(id);
    }
}
