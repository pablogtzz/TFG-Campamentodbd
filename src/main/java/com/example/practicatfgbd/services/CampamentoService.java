package com.example.practicatfgbd.services;

import com.example.practicatfgbd.entities.Campamento;
import com.example.practicatfgbd.repositories.CampamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampamentoService {

    @Autowired
    private CampamentoRepository campamentoRepository;

    public Campamento addCamapamento(Campamento campamento) {
        return campamentoRepository.save(campamento);
    }

    public Campamento editCampamento(Campamento campamento) {
        return campamentoRepository.save(campamento);
    }

    public void deleteCamapento(Long id) {
        campamentoRepository.deleteById(id);
    }

    public Optional<Campamento> findById(Long id) {
        return campamentoRepository.findById(id);
    }

    public List<Campamento> findAll() {
        return campamentoRepository.findAll();
    }
}
