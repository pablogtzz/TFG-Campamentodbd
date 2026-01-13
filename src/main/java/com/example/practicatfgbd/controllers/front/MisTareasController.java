package com.example.practicatfgbd.controllers.front;

import com.example.practicatfgbd.entities.EstadoTarea;
import com.example.practicatfgbd.entities.Rol;
import com.example.practicatfgbd.entities.TareaMonitor;
import com.example.practicatfgbd.entities.User;
import com.example.practicatfgbd.services.TareaMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MisTareasController {

    @Autowired
    private TareaMonitorService tareaMonitorService;

    @GetMapping("/mis-tareas")
    public String mostrarMisTareas(Model model) {
        // Obtener el usuario autenticado (monitor)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User monitor = (User) authentication.getPrincipal();

            // Obtener las tareas asignadas a este monitor
            List<TareaMonitor> tareas = tareaMonitorService.findByUser(monitor);

            // Agregar las tareas al modelo para que sean mostradas en la vista
            model.addAttribute("tareas", tareas);

            boolean seguridad = monitor.getRole() == Rol.COORDINADOR;
            model.addAttribute("user", seguridad);
        }

        // Devolver la vista de mis tareas
        return "misTareas";
    }

    @PostMapping("/comenzar/{id}")
    public String comenzarTarea(@PathVariable Long id) {
        TareaMonitor tarea = tareaMonitorService.findById(id).orElse(null);
        if (tarea != null && tarea.getEstadoTarea() == EstadoTarea.PENDIENTE) {
            tarea.setEstadoTarea(EstadoTarea.EN_PROGRESO);
            tareaMonitorService.addTareaMonitor(tarea);
        }
        return "redirect:/mis-tareas";
    }

    @PostMapping("/terminar/{id}")
    public String terminarTarea(@PathVariable Long id) {
        TareaMonitor tarea = tareaMonitorService.findById(id).orElse(null);
        if (tarea != null && tarea.getEstadoTarea() == EstadoTarea.EN_PROGRESO) {
            tarea.setEstadoTarea(EstadoTarea.COMPLETADA);
            tareaMonitorService.addTareaMonitor(tarea);
        }
        return "redirect:/mis-tareas";
    }

    @PostMapping("/mis-tareas/{id}")
    public String deleteTareaMonitor(@PathVariable Long id) {
        // Lógica para eliminar el juego con el id especificado
        // Puedes llamar a tu servicio para realizar la eliminación desde la base de datos
        tareaMonitorService.deleteTareaMonitor(id);

        // Redirigir a la página de juegos actualizada o a donde sea apropiado
        return "redirect:/mis-tareas";
    }
}

