package com.example.practicatfgbd.repositories;


import com.example.practicatfgbd.entities.Juegos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuegosRepository extends JpaRepository<Juegos, Long> {
    // Puedes agregar métodos personalizados aquí si necesitas realizar consultas específicas
}
