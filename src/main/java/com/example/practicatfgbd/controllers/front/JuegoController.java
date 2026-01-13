package com.example.practicatfgbd.controllers.front;

import com.example.practicatfgbd.entities.*;
import com.example.practicatfgbd.services.JuegosService;
import com.example.practicatfgbd.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class JuegoController {

    @Autowired
    private JuegosService juegosService;

    @Autowired
    private MaterialService materialService;

    @GetMapping("/juegos")
    public String getAllJuegos(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            boolean seguridad = false;
            if(user.getRole() == Rol.COORDINADOR){
                seguridad = true;
            }
            model.addAttribute("user", seguridad);
        }

        List<Juegos> juegosList = juegosService.findAll();
        model.addAttribute("juegos", juegosList);
        return "juegos"; // Nombre de la plantilla Thymeleaf
    }

    @PostMapping("/juegos")
    public String agregarJuego(@RequestParam("tipo") TipoActividad tipo,
                               @RequestParam("titulo") String titulo,
                               @RequestParam("duracion") int duracion,
                               @RequestParam("espacios") String espacios,
                               @RequestParam("descripcion") String descripcion,
                               Model model) {
        String rutaImagen = "/images/dinamica.png";
        if (tipo.equals(TipoActividad.DINAMICA)) {
            rutaImagen = "/images/dinamica.png";
        } else if (tipo.equals(TipoActividad.JUEGO_EXTERIOR)) {
            rutaImagen = "/images/juego_exterior.png";
        } else if (tipo.equals(TipoActividad.JUEGO_INTERIOR)) {
            rutaImagen = "/images/juego_interior.png";
        } else if (tipo.equals(TipoActividad.JUEGO_NOCTURNO)) {
            rutaImagen = "/images/juego_nocturno.png";
        } else if (tipo.equals(TipoActividad.BALADAS)) {
            rutaImagen = "/images/balada.png";
        }

        Juegos juego = Juegos.builder()
                .tipo(tipo)
                .titulo(titulo)
                .duracion(duracion)
                .espacios(espacios)
                .imagenRuta(rutaImagen)
                .descripcion(descripcion)
                .build();

        juegosService.save(juego);

        return "redirect:/juegos"; // Redirigir a la lista de juegos después de guardar
    }

    @PostMapping("/juegos/{id}")
    public String deleteJuego(@PathVariable Long id) {
        // Lógica para eliminar el juego con el id especificado
        // Puedes llamar a tu servicio para realizar la eliminación desde la base de datos
        juegosService.deleteJuegos(id);

        // Redirigir a la página de juegos actualizada o a donde sea apropiado
        return "redirect:/juegos";
    }

}
