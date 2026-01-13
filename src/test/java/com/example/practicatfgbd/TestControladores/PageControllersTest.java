package com.example.practicatfgbd.TestControladores;


import com.example.practicatfgbd.controllers.front.PageControllers;
import com.example.practicatfgbd.entities.Grupo;
import com.example.practicatfgbd.entities.Rol;
import com.example.practicatfgbd.entities.User;
import com.example.practicatfgbd.services.GrupoService;
import com.example.practicatfgbd.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PageControllersTest {

    @InjectMocks
    private PageControllers controller;

    @Mock
    private UserService userService;

    @Mock
    private GrupoService grupoService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowSignupForm() {
        List<Grupo> grupos = new ArrayList<>();
        grupos.add(new Grupo(1L, "Grupo 1", 10));
        grupos.add(new Grupo(2L, "Grupo 2", 12));

        when(grupoService.findAll()).thenReturn(grupos);

        String viewName = controller.showSignupForm(model);

        verify(model, times(1)).addAttribute("user", new User());
        verify(model, times(1)).addAttribute("groups", grupos);
        assertEquals("register", viewName);
    }

    @Test
    public void testAddUser() {
        String nombre = "Test";
        String apellidos = "User";
        String username = "testuser";
        String password = "password";
        String email = "testuser@example.com";
        String telefono = "123456789";
        String role = "MONITOR";
        Long grupoId = 1L;

        Grupo grupo = new Grupo(grupoId, "Grupo 1", 10);
        Optional<Grupo> optionalGrupo = Optional.of(grupo);
        when(grupoService.findById(grupoId)).thenReturn(optionalGrupo);

        String redirectURL = controller.addUser(nombre, apellidos, username, password, email, telefono, role, grupoId);

        User user = User.builder()
                .nombre(nombre)
                .apellidos(apellidos)
                .username(username)
                .password(password)
                .email(email)
                .telefono(telefono)
                .role(Rol.MONITOR)
                .grupo(grupo)
                .build();

        verify(userService, times(1)).saveUser(user);
        assertEquals("redirect:/usuario/login", redirectURL);
    }
}

