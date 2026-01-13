package com.example.practicatfgbd.repositories;

import com.example.practicatfgbd.entities.SubGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubGrupoRepository extends JpaRepository<SubGrupo, Long> {

    List<SubGrupo> findByGrupoIdGrupo(Long groupId);
}
