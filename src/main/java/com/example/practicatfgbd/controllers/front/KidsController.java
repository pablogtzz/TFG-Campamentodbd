package com.example.practicatfgbd.controllers.front;

import com.example.practicatfgbd.entities.*;
import com.example.practicatfgbd.services.GrupoService;
import com.example.practicatfgbd.services.KidsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class KidsController {

    @Autowired
    private KidsService kidService;

    @Autowired
    private GrupoService grupoService;

    @GetMapping("/kids")
    public String getKids(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            boolean seguridad = false;
            if(user.getRole() == Rol.COORDINADOR){
                seguridad = true;
            }
            model.addAttribute("user", seguridad);
        }

        List<Kids> kids = kidService.findAll();
        List<Grupo> groups = grupoService.findAll();

        model.addAttribute("kids", kids);
        model.addAttribute("groups", groups);

        return "kids"; // Nombre del template Thymeleaf
    }

    @PostMapping("/kids")
    public String addKid(@RequestParam String nombre,
                         @RequestParam String apellidos,
                         @RequestParam String fechaNacimiento,
                         @RequestParam String tallaCamiseta,
                         @RequestParam String nombrePadre,
                         @RequestParam String telefonoPadre,
                         @RequestParam String nombreMadre,
                         @RequestParam String telefonoMadre,
                         @RequestParam Long grupoId) {

        // Convertir fecha de nacimiento de String a LocalDate
        LocalDate fechaNacimientoDate = LocalDate.parse(fechaNacimiento);

        Optional<Grupo> grupo = grupoService.findById(grupoId);

        // Construir el objeto Kids usando el builder
        if(grupo.isPresent()) {
            Kids kid = Kids.builder()
                    .nombre(nombre)
                    .apellidos(apellidos)
                    .fechaNacimiento(fechaNacimientoDate)
                    .tallaCamiseta(tallaCamiseta)
                    .nombrePadre(nombrePadre)
                    .telefonoPadre(telefonoPadre)
                    .nombreMadre(nombreMadre)
                    .telefonoMadre(telefonoMadre)
                    .grupo(grupo.get())
                    .build();

            // Agregar el niño a través del servicio
            kidService.addKids(kid);
        }

        // Redirigir de vuelta a la lista de niños después de añadir
        return "redirect:/kids";
    }

    @PostMapping("/kids/{id}")
    public String deleteKids(@PathVariable Long id) {
        // Lógica para eliminar el juego con el id especificado
        // Puedes llamar a tu servicio para realizar la eliminación desde la base de datos
        kidService.deleteKids(id);

        // Redirigir a la página de juegos actualizada o a donde sea apropiado
        return "redirect:/kids";
    }
}
