package com.example.practicatfgbd.TestControladores;

import com.example.practicatfgbd.controllers.front.TareasGruposController;
import com.example.practicatfgbd.entities.EstadoTarea;
import com.example.practicatfgbd.entities.Rol;
import com.example.practicatfgbd.entities.TareaKids;
import com.example.practicatfgbd.entities.User;
import com.example.practicatfgbd.services.TareaKidsService;
import com.example.practicatfgbd.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TareasGruposControllerTest {

    @InjectMocks
    private TareasGruposController controller;

    @Mock
    private TareaKidsService tareaKidsService;

    @Mock
    private UserService userService;

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
    public void testMostrarTareas() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setRole(Rol.MONITOR);

        // Crear tareas usando el builder
        TareaKids tarea1 = TareaKids.builder()
                .idTareaKids(1L)
                .nombre("Tarea 1")
                .estadoTarea(EstadoTarea.PENDIENTE)
                .user(user)
                .build();

        List<TareaKids> tareas = new ArrayList<>();
        tareas.add(tarea1);

        when(authentication.getPrincipal()).thenReturn(user);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(tareaKidsService.findAllByMonitor(user)).thenReturn(tareas);

        String viewName = controller.mostrarTareas(model);

        verify(model, times(1)).addAttribute("tareas", tareas);
        verify(model, times(1)).addAttribute("user", false); // Asegurar que no es coordinador

        assertEquals("tareasKids", viewName);
    }

    @Test
    public void testComenzarTarea() {
        Long id = 1L;
        User user = new User();
        user.setId(1L);

        // Crear tarea usando el builder
        TareaKids tarea = TareaKids.builder()
                .idTareaKids(1L)
                .nombre("Tarea 1")
                .estadoTarea(EstadoTarea.PENDIENTE)
                .user(user)
                .build();

        when(tareaKidsService.findById(id)).thenReturn(Optional.of(tarea));

        String redirectURL = controller.comenzarTarea(id);

        assertEquals(EstadoTarea.EN_PROGRESO, tarea.getEstadoTarea());
        assertEquals("redirect:/grupos-tareas", redirectURL);
    }

    @Test
    public void testTerminarTarea() {
        Long id = 1L;
        User user = new User();
        user.setId(1L);

        // Crear tarea usando el builder
        TareaKids tarea = TareaKids.builder()
                .idTareaKids(1L)
                .nombre("Tarea 1")
                .estadoTarea(EstadoTarea.EN_PROGRESO)
                .user(user)
                .build();

        when(tareaKidsService.findById(id)).thenReturn(Optional.of(tarea));

        String redirectURL = controller.terminarTarea(id);

        assertEquals(EstadoTarea.COMPLETADA, tarea.getEstadoTarea());
        assertEquals("redirect:/grupos-tareas", redirectURL);
    }
}
