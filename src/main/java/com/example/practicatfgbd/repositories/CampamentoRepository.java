package com.example.practicatfgbd.repositories;

import com.example.practicatfgbd.entities.Campamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampamentoRepository extends JpaRepository<Campamento, Long> {
}
