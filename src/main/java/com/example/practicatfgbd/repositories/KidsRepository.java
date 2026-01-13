package com.example.practicatfgbd.repositories;


import com.example.practicatfgbd.entities.Kids;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KidsRepository extends JpaRepository<Kids, Long> {
}
