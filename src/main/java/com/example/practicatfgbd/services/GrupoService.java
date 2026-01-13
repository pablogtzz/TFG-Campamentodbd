package com.example.practicatfgbd.services;

import com.example.practicatfgbd.entities.Grupo;
import com.example.practicatfgbd.entities.SubGrupo;
import com.example.practicatfgbd.repositories.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    public Grupo addGrupo(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public Grupo editGrupo(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public void deleteGrupo(Long id) {
        grupoRepository.deleteById(id);
    }

    public Optional<Grupo> findById(Long id) {
        return grupoRepository.findById(id);
    }

    public List<Grupo> findAll() {
        return grupoRepository.findAll();
    }

}