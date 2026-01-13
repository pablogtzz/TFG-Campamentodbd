package com.example.practicatfgbd.services;

import com.example.practicatfgbd.entities.SubGrupo;
import com.example.practicatfgbd.repositories.SubGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubGrupoService {

    @Autowired
    private SubGrupoRepository subGrupoRepository;

    public SubGrupo addSubGrupo(SubGrupo subGrupo) {
        return subGrupoRepository.save(subGrupo);
    }

    public SubGrupo editSubGrupo(SubGrupo subGrupo) {
        return subGrupoRepository.save(subGrupo);
    }

    public void deleteSubGrupo(Long id) {
        subGrupoRepository.deleteById(id);
    }

    public Optional<SubGrupo> findById(Long id) {
        return subGrupoRepository.findById(id);
    }

    public List<SubGrupo> findAll() {
        return subGrupoRepository.findAll();
    }

    public List<SubGrupo> findSubgruposByGroupId(Long groupId) {
        return subGrupoRepository.findByGrupoIdGrupo(groupId);
    }
}

