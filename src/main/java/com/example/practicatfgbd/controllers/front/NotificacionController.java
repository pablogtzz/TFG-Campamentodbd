package com.example.practicatfgbd.controllers.front;

import com.example.practicatfgbd.entities.Rol;
import com.example.practicatfgbd.entities.User;
import com.example.practicatfgbd.services.NotificacionService;
import com.example.practicatfgbd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class NotificacionController {

    private final NotificacionService notificacionService;
    private final UserService userService;

    @Autowired
    public NotificacionController(NotificacionService notificacionService, UserService userService) {
        this.notificacionService = notificacionService;
        this.userService = userService;
    }

    @GetMapping("/notificaciones")
    public String verNotificaciones(Model model, Principal principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            boolean seguridad = false;
            if(user.getRole() == Rol.COORDINADOR){
                seguridad = true;
            }
            model.addAttribute("user", seguridad);
        }
        // Obtener el usuario actual
        Optional<User> usuario = userService.findByUsername(principal.getName());

        // Obtener las notificaciones no leídas
        if(usuario.isPresent()) {
            model.addAttribute("notificaciones", notificacionService.obtenerNotificacionesNoLeidas(usuario.get()));
        }

        return "notificacion";
    }

    @PostMapping("/notificaciones/marcar/{id}")
    public String marcarNotificacionComoLeida(@PathVariable("id") Long idNotificacion) {
        notificacionService.marcarNotificacionComoLeida(idNotificacion);
        return "redirect:/notificaciones";
    }
}

