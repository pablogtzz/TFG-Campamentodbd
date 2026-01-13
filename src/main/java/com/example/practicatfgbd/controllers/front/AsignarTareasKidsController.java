package com.example.practicatfgbd.controllers.front;

import com.example.practicatfgbd.dto.TareaGrupoDTO;
import com.example.practicatfgbd.entities.*;
import com.example.practicatfgbd.services.SubGrupoService;
import com.example.practicatfgbd.services.TareaKidsService;
import com.example.practicatfgbd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AsignarTareasKidsController {

    @Autowired
    private TareaKidsService tareaService;
    @Autowired
    private UserService userService;
    @Autowired
    private SubGrupoService subGrupoService;

    @GetMapping("/asignar-tareas-grupos")
    public String mostrarTareas(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            boolean seguridad = false;
            if(user.getRole() == Rol.COORDINADOR){
                seguridad = true;
            }
            model.addAttribute("user", seguridad);
        }
        List<TareaKids> tareas = tareaService.findAll();
        List<SubGrupo> subgrupos = subGrupoService.findAll();
        List<User> monitores = userService.findAll();
        model.addAttribute("tareas", tareas);
        model.addAttribute("subgrupos", subgrupos);
        model.addAttribute("monitores", monitores);
        return "AsignarTareasGrupos";
    }

    @PostMapping("/asignar-tareas-grupos")
    public String crearTarea(@ModelAttribute TareaGrupoDTO tareaFormulario) {
        TareaKids nuevaTarea = new TareaKids();
        nuevaTarea.setNombre(tareaFormulario.getNombre());
        nuevaTarea.setDescripcion(tareaFormulario.getDescripcion());
        nuevaTarea.setDia(tareaFormulario.getDia());

        // Obtener y asignar subgrupo
        if (tareaFormulario.getGrupoId() != null) {
            SubGrupo subGrupo = subGrupoService.findById(tareaFormulario.getGrupoId()).orElse(null);
            nuevaTarea.setSubGrupo(subGrupo);
        }

        // Obtener y asignar monitor
        if (tareaFormulario.getUserId() != null) {
            User monitor = userService.findById(tareaFormulario.getUserId()).orElse(null);
            nuevaTarea.setUser(monitor);
        }

        nuevaTarea.setEstadoTarea(EstadoTarea.PENDIENTE); // Estado inicial

        tareaService.addTareaKids(nuevaTarea);
        return "redirect:/asignar-tareas-grupos";
    }

    @PostMapping("/asignar-tareas-grupos/{id}")
    public String deleteTareaGrupo(@PathVariable Long id) {
        // Lógica para eliminar el juego con el id especificado
        // Puedes llamar a tu servicio para realizar la eliminación desde la base de datos
        tareaService.deleteTareaKids(id);

        // Redirigir a la página de juegos actualizada o a donde sea apropiado
        return "redirect:/asignar-tareas-grupos";
    }
}
