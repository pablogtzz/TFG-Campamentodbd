package com.example.practicatfgbd.services;

import com.example.practicatfgbd.entities.Notificacion;
import com.example.practicatfgbd.entities.User;
import com.example.practicatfgbd.repositories.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    @Autowired
    public NotificacionService(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    @Transactional
    public void crearNotificacion(String contenido, User usuario) {
        Notificacion notificacion = new Notificacion();
        notificacion.setContenido(contenido);
        notificacion.setFechaCreacion(LocalDateTime.now());
        notificacion.setLeido(false);
        notificacion.setUsuario(usuario);
        notificacionRepository.save(notificacion);
    }

    @Transactional(readOnly = true)
    public List<Notificacion> obtenerNotificacionesNoLeidas(User usuario) {
        return notificacionRepository.findByUsuarioAndLeidoOrderByFechaCreacionDesc(usuario, false);
    }

    @Transactional
    public void marcarNotificacionComoLeida(Long idNotificacion) {
        notificacionRepository.findById(idNotificacion).ifPresent(notificacion -> {
            notificacion.setLeido(true);
            notificacionRepository.save(notificacion);
        });
    }
}

