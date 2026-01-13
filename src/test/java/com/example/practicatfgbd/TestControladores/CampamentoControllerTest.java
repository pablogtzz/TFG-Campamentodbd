package com.example.practicatfgbd.TestControladores;

import com.example.practicatfgbd.controllers.front.CampamentoController;
import com.example.practicatfgbd.entities.Rol;
import com.example.practicatfgbd.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CampamentoControllerTest {

    @InjectMocks
    private CampamentoController campamentoController;

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
    public void testGetCampamentoPage_CoordinatorRole() {
        // Mock de usuario con rol de coordinador
        User user = new User();
        user.setRole(Rol.COORDINADOR);

        // Configuración del contexto de seguridad
        when(authentication.getPrincipal()).thenReturn(user);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Llamada al método del controlador
        String viewName = campamentoController.getCampamentoPage(model);

        // Verificación de atributos agregados al modelo
        verify(model, times(1)).addAttribute("user", true);
        assertEquals("campamento", viewName);
    }

    @Test
    public void testGetCampamentoPage_NonCoordinatorRole() {
        // Mock de usuario con rol que no es de coordinador
        User user = new User();
        user.setRole(Rol.MONITOR); // Ejemplo de otro rol diferente a coordinador

        // Configuración del contexto de seguridad
        when(authentication.getPrincipal()).thenReturn(user);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Llamada al método del controlador
        String viewName = campamentoController.getCampamentoPage(model);

        // Verificación de atributos agregados al modelo
        verify(model, times(1)).addAttribute("user", false);
        assertEquals("campamento", viewName);
    }

    @Test
    public void testGetCampamentoPage_AuthenticationNull() {
        // Configuración del contexto de seguridad con autenticación nula
        when(securityContext.getAuthentication()).thenReturn(null);
        SecurityContextHolder.setContext(securityContext);

        // Llamada al método del controlador
        String viewName = campamentoController.getCampamentoPage(model);

        // Verificación de atributos agregados al modelo
        verify(model, never()).addAttribute(anyString(), any());
        assertEquals("campamento", viewName);
    }
}

