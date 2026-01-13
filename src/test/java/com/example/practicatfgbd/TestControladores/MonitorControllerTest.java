package com.example.practicatfgbd.TestControladores;

import com.example.practicatfgbd.controllers.front.MonitorController;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MonitorControllerTest {

    @InjectMocks
    private MonitorController controller;

    @Mock
    private UserService userService;

    @Mock
    private GrupoService grupoService;

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
    public void testGetMonitores() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setRole(Rol.COORDINADOR);

        // Crear un usuario monitor para la lista de usuarios
        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("testuser2");
        user2.setRole(Rol.MONITOR);

        List<User> users = new ArrayList<>();
        users.add(user2); // Agregar user2 a la lista de usuarios

        List<Grupo> grupos = new ArrayList<>();
        grupos.add(new Grupo(1L, "Grupo 1", 12));

        // Simular el comportamiento del usuario autenticado
        when(authentication.getPrincipal()).thenReturn(user);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Simular el comportamiento del servicio para obtener todos los monitores y grupos
        when(userService.findAll()).thenReturn(users);
        when(grupoService.findAll()).thenReturn(grupos);

        // Ejecutar el método del controlador que queremos probar
        String viewName = controller.getKids(model, null);

        // Verificar que se agregaron los atributos correctos al modelo
        verify(model, times(1)).addAttribute("users", users);
        verify(model, times(1)).addAttribute("groups", grupos);
        verify(model, times(1)).addAttribute("user", true); // Asegurar que es coordinador

        // Verificar que la vista devuelta sea la esperada
        assertEquals("monitor", viewName);
    }

    @Test
    public void testAddMonitor() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setRole(Rol.COORDINADOR);

        // Simulamos el comportamiento del usuario autenticado
        when(authentication.getPrincipal()).thenReturn(user);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Creamos un monitor nuevo para agregar
        List<User> users = new ArrayList<>();
        User user2 = new User();
        user.setId(2L);
        user.setUsername("testuser2");
        user.setRole(Rol.MONITOR);

        // Ejecutamos el método del controlador que queremos probar
        String redirectURL = controller.addMonitor(user2);

        // Verificamos que el servicio de usuario haya guardado al nuevo monitor
        verify(userService, times(1)).saveUser(user2);

        // Verificamos que la URL de redirección sea la esperada
        assertEquals("redirect:/monitores", redirectURL);
    }

    @Test
    public void testDeleteMonitor() throws Exception {
        Long id = 1L;

        // Mock del RedirectAttributes
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        // Caso exitoso sin excepción
        String viewName = controller.deleteMonitor(id, redirectAttributes);
        verify(userService, times(1)).deleteUser(id);
        assertEquals("redirect:/monitores", viewName);

        // Verificar que no hay errores
        verify(redirectAttributes, never()).addFlashAttribute(eq("errorMessage"), anyString());

        // Caso con excepción
        doThrow(new RuntimeException("Error al eliminar usuario")).when(userService).deleteUser(id);

        viewName = controller.deleteMonitor(id, redirectAttributes);
        assertEquals("redirect:/monitores", viewName);

        // Verificar que el mensaje de error se añadió a RedirectAttributes
        verify(redirectAttributes, times(1)).addFlashAttribute("errorMessage", "No se puede eliminar el monitor porque está asociado a una tarea.");
    }
}

