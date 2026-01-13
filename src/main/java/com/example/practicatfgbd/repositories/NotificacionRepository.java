package com.example.practicatfgbd.repositories;

import com.example.practicatfgbd.entities.Notificacion;
import com.example.practicatfgbd.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    List<Notificacion> findByUsuarioAndLeidoOrderByFechaCreacionDesc(User usuario, boolean leido);
}

