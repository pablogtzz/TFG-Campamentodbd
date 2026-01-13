package com.example.practicatfgbd.services;

import com.example.practicatfgbd.entities.Material;
import com.example.practicatfgbd.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;


    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    public Optional<Material> findById(Long id) {
        return materialRepository.findById(id);
    }

    public Material addMaterial(Material material) {
        return materialRepository.save(material);
    }

    public Material editMaterial(Material material) {
        return materialRepository.save(material);
    }

    public void deleteMaterial(Long id) {
        materialRepository.deleteById(id);
    }

    public Optional<Material> findByNombre(String nombre) {
        return materialRepository.findByNombre(nombre);
    }
}
