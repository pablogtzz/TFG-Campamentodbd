package com.example.practicatfgbd.services;

import com.example.practicatfgbd.entities.TareaMonitor;
import com.example.practicatfgbd.entities.User;
import com.example.practicatfgbd.repositories.TareaMonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaMonitorService {

    @Autowired
    private TareaMonitorRepository tareaMonitorRepository;

    public TareaMonitor addTareaMonitor(TareaMonitor tareaMonitor) {
        return tareaMonitorRepository.save(tareaMonitor);
    }

    public TareaMonitor editTareaMonitor(TareaMonitor tareaMonitor) {
        return tareaMonitorRepository.save(tareaMonitor);
    }

    public void deleteTareaMonitor(Long id) {
        tareaMonitorRepository.deleteById(id);
    }

    public Optional<TareaMonitor> findById(Long id) {
        return tareaMonitorRepository.findById(id);
    }

    public List<TareaMonitor> findAll() {
        return tareaMonitorRepository.findAll();
    }

    public List<TareaMonitor> findByUser(User monitor) {
        return tareaMonitorRepository.findByUser(monitor);
    }
}

