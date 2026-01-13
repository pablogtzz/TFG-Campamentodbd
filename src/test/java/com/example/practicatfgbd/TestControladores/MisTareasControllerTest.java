package com.example.practicatfgbd.TestControladores;

import com.example.practicatfgbd.controllers.front.MisTareasController;
import com.example.practicatfgbd.entities.EstadoTarea;
import com.example.practicatfgbd.entities.Rol;
import com.example.practicatfgbd.entities.TareaMonitor;
import com.example.practicatfgbd.entities.User;
import com.example.practicatfgbd.services.TareaMonitorService;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MisTareasControllerTest {

    @InjectMocks
    private MisTareasController controller;

    @Mock
    private TareaMonitorService tareaMonitorService;

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
    public void testMostrarMisTareas() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setRole(Rol.MONITOR);

        List<TareaMonitor> tareas = new ArrayList<>();
        tareas.add(new TareaMonitor(1L, "Tarea 1", null, null, EstadoTarea.PENDIENTE, user, null));

        when(authentication.getPrincipal()).thenReturn(user);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(tareaMonitorService.findByUser(user)).thenReturn(tareas);

        String viewName = controller.mostrarMisTareas(model);

        verify(model, times(1)).addAttribute("tareas", tareas);
        verify(model, times(1)).addAttribute("user", false); // Asegurar que no es coordinador

        assertEquals("misTareas", viewName);
    }

    @Test
    public void testComenzarTarea() {
        Long id = 1L;
        TareaMonitor tarea = new TareaMonitor(id, "Tarea 1", null, null, EstadoTarea.PENDIENTE, null, null);

        when(tareaMonitorService.findById(id)).thenReturn(Optional.of(tarea));

        String redirectURL = controller.comenzarTarea(id);

        assertEquals(EstadoTarea.EN_PROGRESO, tarea.getEstadoTarea());
        assertEquals("redirect:/mis-tareas", redirectURL);
    }

    @Test
    public void testTerminarTarea() {
        Long id = 1L;
        TareaMonitor tarea = new TareaMonitor(id, "Tarea 1", null, null, EstadoTarea.EN_PROGRESO, null, null);

        when(tareaMonitorService.findById(id)).thenReturn(Optional.of(tarea));

        String redirectURL = controller.terminarTarea(id);

        assertEquals(EstadoTarea.COMPLETADA, tarea.getEstadoTarea());
        assertEquals("redirect:/mis-tareas", redirectURL);
    }
}
