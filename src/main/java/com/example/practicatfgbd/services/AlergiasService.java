package com.example.practicatfgbd.services;

import com.example.practicatfgbd.entities.Alergias;
import com.example.practicatfgbd.repositories.AlergiasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlergiasService {

    @Autowired
    private AlergiasRepository alergiasRepository;

    public Alergias addAlergias(Alergias alergias) {
        return alergiasRepository.save(alergias);
    }

    public Alergias editAlergias(Alergias alergias) {
        return alergiasRepository.save(alergias);
    }

    public void deleteAlergias(Long id) {
        alergiasRepository.deleteById(id);
    }

    public Optional<Alergias> findById(Long id) {
        return alergiasRepository.findById(id);
    }

    public List<Alergias> findAll() {
        return alergiasRepository.findAll();
    }
}