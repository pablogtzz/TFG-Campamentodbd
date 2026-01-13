package com.example.practicatfgbd.controllers.front;

import com.example.practicatfgbd.entities.Grupo;
import com.example.practicatfgbd.entities.Rol;
import com.example.practicatfgbd.entities.User;
import com.example.practicatfgbd.services.GrupoService;
import com.example.practicatfgbd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("usuario")
public class PageControllers {

    @Autowired
    private UserService userService;

    @Autowired
    private GrupoService grupoService;



    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Este es el nombre de la vista del formulario de inicio de sesión (login.html)
    }

    @PostMapping("/login")
    public String login() {
        // Aquí puedes agregar lógica de autenticación si es necesario
        return "redirect:/home"; // Redirige a la página de inicio después del inicio de sesión exitoso
    }

    @PostMapping("/logout")
    public String logout() {
        // Puedes agregar algún código adicional aquí si lo necesitas
        return "redirect:/home"; // Redirige al formulario de inicio de sesión con un mensaje de éxito de cierre de sesión
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        List<Grupo> groups = grupoService.findAll();

        model.addAttribute("groups", groups);
        return "register"; // Este es el nombre de la vista del formulario de registro (signup.html)
    }

    @PostMapping("/signup")
    public String addUser(@RequestParam String nombre,
                          @RequestParam String apellidos,
                          @RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String email,
                          @RequestParam String telefono,
                          @RequestParam String role,
                          @RequestParam Long grupoId) {

        // Convertir fecha de nacimiento de String a LocalDate

        Optional<Grupo> grupo = grupoService.findById(grupoId);
        Rol rol;
        if(role.equals("COORDINADOR")){
            rol = Rol.COORDINADOR;
        }else{
            rol = Rol.MONITOR;
        }

        // Construir el objeto Kids usando el builder
        if(grupo.isPresent()) {
            User user = User.builder()
                    .nombre(nombre)
                    .apellidos(apellidos)
                    .username(username)
                    .password(password)
                    .email(email)
                    .telefono(telefono)
                    .role(rol)
                    .grupo(grupo.get())
                    .build();

            // Agregar el niño a través del servicio
            userService.saveUser(user);
        }

        // Redirigir de vuelta a la lista de niños después de añadir
        return "redirect:/usuario/login";
    }
}
