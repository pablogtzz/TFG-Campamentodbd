package com.example.practicatfgbd.services;


import com.example.practicatfgbd.entities.Kids;
import com.example.practicatfgbd.repositories.KidsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KidsService {

    @Autowired
    private KidsRepository kidsRepository;

    public Kids addKids(Kids kids) {
        return kidsRepository.save(kids);
    }

    public Kids editKids(Kids kids) {
        return kidsRepository.save(kids);
    }

    public void deleteKids(Long id) {
        kidsRepository.deleteById(id);
    }

    public Optional<Kids> findById(Long id) {
        return kidsRepository.findById(id);
    }

    public List<Kids> findAll() {
        return kidsRepository.findAll();
    }
}