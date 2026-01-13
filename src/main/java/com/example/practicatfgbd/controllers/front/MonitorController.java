package com.example.practicatfgbd.controllers.front;

import com.example.practicatfgbd.entities.*;
import com.example.practicatfgbd.services.GrupoService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MonitorController {

    @Autowired
    private UserService userService;

    @Autowired
    private GrupoService grupoService;

    @GetMapping("/monitores")
    public String getKids(Model model, @ModelAttribute("errorMessage") String errorMessage) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            boolean seguridad = false;
            if(user.getRole() == Rol.COORDINADOR){
                seguridad = true;
            }
            model.addAttribute("user", seguridad);
        }

        List<User> users = userService.findAll();
        List<Grupo> groups = grupoService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("groups", groups);

        if (errorMessage != null && !errorMessage.isEmpty()) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "monitor";
    }

    @PostMapping("/monitores")
    public String addMonitor(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/monitores";
    }

    @PostMapping("/monitores/{id}")
    public String deleteMonitor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // Lógica para eliminar el juego con el id especificado
        // Puedes llamar a tu servicio para realizar la eliminación desde la base de datos
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            // Manejar la excepción cuando el subgrupo está asociado a una tarea
            redirectAttributes.addFlashAttribute("errorMessage", "No se puede eliminar el monitor porque está asociado a una tarea.");
            return "redirect:/monitores";
        }

        // Redirigir a la página de juegos actualizada o a donde sea apropiado
        return "redirect:/monitores";
    }
}
