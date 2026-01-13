package com.example.practicatfgbd.controllers.front;


import com.example.practicatfgbd.entities.*;
import com.example.practicatfgbd.services.TareaKidsService;
import com.example.practicatfgbd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TareasGruposController {

    @Autowired
    private TareaKidsService tareaService;

    @Autowired
    private UserService userService;

    @GetMapping("/grupos-tareas")
    public String mostrarTareas(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            List<TareaKids> tareas = tareaService.findAllByMonitor(user); // Obtener todas las tareas del monitor actual
            model.addAttribute("tareas", tareas);

            boolean seguridad = user.getRole() == Rol.COORDINADOR;
            model.addAttribute("user", seguridad);
        }
        return "tareasKids";
    }

    @PostMapping("/comenzar-tarea/{id}")
    public String comenzarTarea(@PathVariable("id") Long idTarea) {
        TareaKids tarea = tareaService.findById(idTarea).orElse(null);
        if (tarea != null && tarea.getEstadoTarea() == EstadoTarea.PENDIENTE) {
            tarea.setEstadoTarea(EstadoTarea.EN_PROGRESO);
            tareaService.addTareaKids(tarea);
        }
        return "redirect:/grupos-tareas";
    }

    @PostMapping("/terminar-tarea/{id}")
    public String terminarTarea(@PathVariable("id") Long idTarea) {
        TareaKids tarea = tareaService.findById(idTarea).orElse(null);
        if (tarea != null && tarea.getEstadoTarea() == EstadoTarea.EN_PROGRESO) {
            tarea.setEstadoTarea(EstadoTarea.COMPLETADA);
            tareaService.addTareaKids(tarea);
        }
        return "redirect:/grupos-tareas";
    }

    @PostMapping("/grupos-tareas/{id}")
    public String deleteTareaMonitor(@PathVariable Long id) {
        // Lógica para eliminar el juego con el id especificado
        // Puedes llamar a tu servicio para realizar la eliminación desde la base de datos
        tareaService.deleteTareaKids(id);

        // Redirigir a la página de juegos actualizada o a donde sea apropiado
        return "redirect:/grupos-tareas";
    }
}
