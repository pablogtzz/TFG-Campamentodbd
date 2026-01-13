package com.example.practicatfgbd.TestControladores;

import com.example.practicatfgbd.controllers.front.AsignarTareasMonitorController;
import com.example.practicatfgbd.dto.TareaMonitorDTO;
import com.example.practicatfgbd.entities.*;
import com.example.practicatfgbd.services.SubGrupoService;
import com.example.practicatfgbd.services.TareaMonitorService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AsignarTareasMonitorControllerTest {

    @InjectMocks
    private AsignarTareasMonitorController asignarTareasMonitorController;

    @Mock
    private TareaMonitorService tareaService;

    @Mock
    private UserService userService;

    @Mock
    private SubGrupoService subGrupoService;

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
        List<TareaMonitor> tareas = new ArrayList<>();
        List<SubGrupo> subgrupos = new ArrayList<>();
        List<User> monitores = new ArrayList<>();

        when(tareaService.findAll()).thenReturn(tareas);
        when(subGrupoService.findAll()).thenReturn(subgrupos);
        when(userService.findAll()).thenReturn(monitores);

        User user = mock(User.class);
        when(user.getRole()).thenReturn(Rol.COORDINADOR);
        when(authentication.getPrincipal()).thenReturn(user);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        String viewName = asignarTareasMonitorController.mostrarTareas(model);

        verify(tareaService, times(1)).findAll();
        verify(subGrupoService, times(1)).findAll();
        verify(userService, times(1)).findAll();
        verify(model, times(1)).addAttribute("tareas", tareas);
        verify(model, times(1)).addAttribute("subgrupos", subgrupos);
        verify(model, times(1)).addAttribute("monitores", monitores);
        verify(model, times(1)).addAttribute(eq("user"), anyBoolean());
        assertEquals("AsignarTareasMonitor", viewName);
    }

    @Test
    public void testCrearTarea() {
        TareaMonitorDTO tareaFormulario = new TareaMonitorDTO();
        tareaFormulario.setNombre("Nombre de la tarea");
        tareaFormulario.setDescripcion("Descripción de la tarea");
        tareaFormulario.setDia(LocalDate.of(2024, 6, 14));
        tareaFormulario.setUserId(1L);

        User monitor = new User();
        when(userService.findById(1L)).thenReturn(Optional.of(monitor));

        String viewName = asignarTareasMonitorController.crearTarea(tareaFormulario);

        verify(tareaService, times(1)).addTareaMonitor(any(TareaMonitor.class));
        assertEquals("redirect:/asignar-tareas-monitores", viewName);
    }
}
