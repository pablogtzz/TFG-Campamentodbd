package com.example.practicatfgbd.controllers.front;

import com.example.practicatfgbd.entities.Grupo;
import com.example.practicatfgbd.entities.Rol;
import com.example.practicatfgbd.entities.SubGrupo;
import com.example.practicatfgbd.entities.User;
import com.example.practicatfgbd.services.GrupoService;
import com.example.practicatfgbd.services.SubGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class GruposController {

    @Autowired
    private GrupoService grupoService;
    @Autowired
    private SubGrupoService subGrupoService;


    @GetMapping("/grupos")
    public String listaGrupos(Model model, @ModelAttribute("errorMessage") String errorMessage) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            boolean seguridad = false;
            if(user.getRole() == Rol.COORDINADOR){
                seguridad = true;
            }
            model.addAttribute("user", seguridad);
        }
        List<Grupo> grupos = grupoService.findAll();
        model.addAttribute("grupos", grupos);

        if (errorMessage != null && !errorMessage.isEmpty()) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "grupos";
    }

    @PostMapping("/add-group")
    public String agregarGrupo(Grupo grupo) {
        grupoService.addGrupo(grupo);
        return "redirect:/grupos";
    }

    @GetMapping("/subgrupos/{idGrupo}")
    public String mostrarSubgrupos(@PathVariable Long idGrupo, Model model, @ModelAttribute("errorMessage") String errorMessage) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            boolean seguridad = false;
            if(user.getRole() == Rol.COORDINADOR){
                seguridad = true;
            }
            model.addAttribute("user", seguridad);
        }
        List<SubGrupo> subgrupos = subGrupoService.findSubgruposByGroupId(idGrupo);
        Optional<Grupo> grupo = grupoService.findById(idGrupo);
        if (grupo.isPresent()) {
            model.addAttribute("subgrupos", subgrupos);
            model.addAttribute("grupo", grupo.get());

            if (errorMessage != null && !errorMessage.isEmpty()) {
                model.addAttribute("errorMessage", errorMessage);
            }
            return "subgrupos";
        } else {
            model.addAttribute("error", "Grupo no encontrado");
            return "redirect:/grupos";
        }
    }

    @PostMapping("/add-subgrupo/{idGrupo}")
    public String agregarSubgrupo(@PathVariable Long idGrupo,
                                  @RequestParam String nombre,
                                  @RequestParam int numeroIntegrantes, Model model) {
        Optional<Grupo> optionalGrupo = grupoService.findById(idGrupo);
        if (optionalGrupo.isPresent()) {
            Grupo grupo = optionalGrupo.get();
            SubGrupo subGrupo = new SubGrupo();
            subGrupo.setNombre(nombre);
            subGrupo.setNumeroIntegrantes(numeroIntegrantes);
            subGrupo.setGrupo(grupo);
            subGrupoService.addSubGrupo(subGrupo);
            return "redirect:/subgrupos/" + idGrupo;
        } else {
            model.addAttribute("error", "Grupo no encontrado");
            return "redirect:/grupos";
        }
    }

    @PostMapping("/grupos/{id}")
    public String deleteGrupo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // Lógica para eliminar el juego con el id especificado
        // Puedes llamar a tu servicio para realizar la eliminación desde la base de datos
        try {
            grupoService.deleteGrupo(id);
        } catch (Exception e) {
            // Manejar la excepción cuando el subgrupo está asociado a una tarea
            redirectAttributes.addFlashAttribute("errorMessage", "No se puede eliminar el grupo porque está asociado o a un subgrupo, o a un niño o a un monitor.");
            return "redirect:/grupos";
        }

        // Redirigir a la página de juegos actualizada o a donde sea apropiado
        return "redirect:/grupos";
    }

    @PostMapping("/subgrupos/{id}")
    public String deleteSubgrupo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<SubGrupo> subGrupoOptional = subGrupoService.findById(id);

        if (subGrupoOptional.isPresent()) {
            SubGrupo subGrupo = subGrupoOptional.get();
            Long idGrupo = subGrupo.getGrupo().getIdGrupo(); // Obtener el id del grupo asociado al subgrupo

            try {
                subGrupoService.deleteSubGrupo(id);
            } catch (Exception e) {
                // Manejar la excepción cuando el subgrupo está asociado a una tarea
                redirectAttributes.addFlashAttribute("errorMessage", "No se puede eliminar el subgrupo porque está asociado a una tarea.");
                return "redirect:/subgrupos/" + idGrupo;
            }
            return "redirect:/subgrupos/" + idGrupo;
        } else {
            // Manejo de error si el subgrupo no se encuentra
            return "redirect:/grupos"; // Redirigir a la lista de grupos o manejar el error de alguna manera
        }
    }
}
