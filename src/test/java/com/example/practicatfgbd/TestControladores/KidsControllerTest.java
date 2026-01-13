package com.example.practicatfgbd.TestControladores;

import com.example.practicatfgbd.controllers.front.KidsController;
import com.example.practicatfgbd.entities.Grupo;
import com.example.practicatfgbd.services.GrupoService;
import com.example.practicatfgbd.services.KidsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class KidsControllerTest {

    @InjectMocks
    private KidsController kidsController;

    @Mock
    private KidsService kidsService;

    @Mock
    private GrupoService grupoService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Inicializar los mocks antes de cada prueba
    }

    @Test
    public void testAddKid() {
        // Datos de ejemplo para el niño
        String nombre = "Juan";
        String apellidos = "Perez";
        String fechaNacimiento = "2023-01-01";
        String tallaCamiseta = "M";
        String nombrePadre = "Pedro";
        String telefonoPadre = "123456789";
        String nombreMadre = "Maria";
        String telefonoMadre = "987654321";
        Long grupoId = 1L;

        // Mock del grupo asociado al niño
        Grupo grupo = new Grupo();
        grupo.setIdGrupo(grupoId);

        // Mock del método findById del GrupoService
        when(grupoService.findById(grupoId)).thenReturn(Optional.of(grupo));

        // Llamada al método del controlador para agregar un niño
        String viewName = kidsController.addKid(nombre, apellidos, fechaNacimiento, tallaCamiseta,
                nombrePadre, telefonoPadre, nombreMadre, telefonoMadre, grupoId);

        // Verificación de que se llamó al método addKids del KidsService con el niño correspondiente
        verify(kidsService, times(1)).addKids(any());

        // Verificación de que se redirige correctamente después de agregar el niño
        assertEquals("redirect:/kids", viewName);
    }

    @Test
    public void testDeleteKid() {
        Long kidId = 1L;

        // Llamada al método del controlador para eliminar un niño por su ID
        String viewName = kidsController.deleteKids(kidId);

        // Verificación de que se llamó al método deleteKids del KidsService con el ID del niño
        verify(kidsService, times(1)).deleteKids(kidId);

        // Verificación de que se redirige correctamente después de eliminar el niño
        assertEquals("redirect:/kids", viewName);
    }
}
