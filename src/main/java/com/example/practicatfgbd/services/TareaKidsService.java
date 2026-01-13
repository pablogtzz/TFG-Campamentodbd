package com.example.practicatfgbd.services;

import com.example.practicatfgbd.entities.TareaKids;
import com.example.practicatfgbd.entities.User;
import com.example.practicatfgbd.repositories.TareaKidsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaKidsService {

    @Autowired
    private TareaKidsRepository tareaKidsRepository;

    public TareaKids addTareaKids(TareaKids tareaKids) {
        return tareaKidsRepository.save(tareaKids);
    }

    public TareaKids editTareaKids(TareaKids tareaKids) {
        return tareaKidsRepository.save(tareaKids);
    }

    public void deleteTareaKids(Long id) {
        tareaKidsRepository.deleteById(id);
    }

    public Optional<TareaKids> findById(Long id) {
        return tareaKidsRepository.findById(id);
    }

    public List<TareaKids> findAll() {
        return tareaKidsRepository.findAll();
    }

    public List<TareaKids> findAllByMonitor(User user) {
        return tareaKidsRepository.findAllByUser(user);
    }
}
