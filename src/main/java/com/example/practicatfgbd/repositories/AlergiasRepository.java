package com.example.practicatfgbd.repositories;

import com.example.practicatfgbd.entities.Alergias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlergiasRepository extends JpaRepository<Alergias, Long> {
}
