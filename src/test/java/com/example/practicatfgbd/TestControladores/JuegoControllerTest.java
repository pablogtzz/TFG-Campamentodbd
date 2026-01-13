package com.example.practicatfgbd.TestControladores;

import com.example.practicatfgbd.controllers.front.JuegoController;
import com.example.practicatfgbd.entities.*;
import com.example.practicatfgbd.services.JuegosService;
import com.example.practicatfgbd.services.MaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JuegoControllerTest {

    @InjectMocks
    private JuegoController juegoController;

    @Mock
    private JuegosService juegosService;

    @Mock
    private MaterialService materialService;

    @Mock
    private Model model;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllJuegos() {
        List<Juegos> juegosList = new ArrayList<>();
        juegosList.add(new Juegos());
        when(juegosService.findAll()).thenReturn(juegosList);

        User user = mock(User.class);
        when(user.getRole()).thenReturn(Rol.COORDINADOR);
        when(authentication.getPrincipal()).thenReturn(user);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        String viewName = juegoController.getAllJuegos(model);

        verify(juegosService, times(1)).findAll();
        verify(model, times(1)).addAttribute("juegos", juegosList);
        verify(model, times(1)).addAttribute(eq("user"), anyBoolean());
        assertEquals("juegos", viewName);
    }

    @Test
    public void testAgregarJuego() {
        String tipo = TipoActividad.DINAMICA.toString();
        String titulo = "Nuevo Juego";
        int duracion = 60;
        String espacios = "Sala A";
        String descripcion = "Descripción del juego";

        String viewName = juegoController.agregarJuego(TipoActividad.DINAMICA, titulo, duracion, espacios, descripcion, model);

        verify(juegosService, times(1)).save(any(Juegos.class));
        assertEquals("redirect:/juegos", viewName);
    }

    @Test
    public void testDeleteJuego() {
        Long id = 1L;

        String viewName = juegoController.deleteJuego(id);

        verify(juegosService, times(1)).deleteJuegos(id);
        assertEquals("redirect:/juegos", viewName);
    }
}
