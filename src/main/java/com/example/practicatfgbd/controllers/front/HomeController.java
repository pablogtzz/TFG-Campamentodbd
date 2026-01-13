package com.example.practicatfgbd.controllers.front;

import com.example.practicatfgbd.entities.Rol;
import com.example.practicatfgbd.entities.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {

    @GetMapping({"/home", "/"})
    public String home(HttpServletRequest request, HttpServletResponse response) {

        return "home"; // Nombre de la vista de la página de inicio (home.html)
    }

    @GetMapping("/sidebar")
    public String sidebar(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            model.addAttribute("user", user);
        }
        return "/fragmentos/sidebar";
    }
}
