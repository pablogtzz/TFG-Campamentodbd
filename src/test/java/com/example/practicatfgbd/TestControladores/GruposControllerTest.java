package com.example.practicatfgbd.TestControladores;

import com.example.practicatfgbd.controllers.front.GruposController;
import com.example.practicatfgbd.entities.Grupo;
import com.example.practicatfgbd.entities.Rol;
import com.example.practicatfgbd.entities.SubGrupo;
import com.example.practicatfgbd.entities.User;
import com.example.practicatfgbd.services.GrupoService;
import com.example.practicatfgbd.services.SubGrupoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class GruposControllerTest {

    @InjectMocks
    private GruposController gruposController;

    @Mock
    private GrupoService grupoService;

    @Mock
    private SubGrupoService subGrupoService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListaGrupos_CoordinatorRole() {
        List<Grupo> grupos = new ArrayList<>();
        when(grupoService.findAll()).thenReturn(grupos);

        User user = new User();
        user.setRole(Rol.COORDINADOR);
        when(model.addAttribute("user", true)).thenReturn(model);

        String viewName = gruposController.listaGrupos(model, null);

        verify(grupoService, times(1)).findAll();
        assertEquals("grupos", viewName);
    }

    @Test
    public void testMostrarSubgrupos() {
        Long idGrupo = 1L;
        List<SubGrupo> subgrupos = new ArrayList<>();
        Optional<Grupo> grupo = Optional.of(new Grupo());

        when(subGrupoService.findSubgruposByGroupId(idGrupo)).thenReturn(subgrupos);
        when(grupoService.findById(idGrupo)).thenReturn(grupo);

        String viewName = gruposController.mostrarSubgrupos(idGrupo, model, null);

        verify(subGrupoService, times(1)).findSubgruposByGroupId(idGrupo);
        verify(grupoService, times(1)).findById(idGrupo);
        assertEquals("subgrupos", viewName);
    }

    @Test
    public void testAgregarSubgrupo() {
        Long idGrupo = 1L;
        String nombre = "Subgrupo A";
        int numeroIntegrantes = 10;

        Optional<Grupo> optionalGrupo = Optional.of(new Grupo());
        when(grupoService.findById(idGrupo)).thenReturn(optionalGrupo);

        String viewName = gruposController.agregarSubgrupo(idGrupo, nombre, numeroIntegrantes, model);

        verify(grupoService, times(1)).findById(idGrupo);
        verify(subGrupoService, times(1)).addSubGrupo(any(SubGrupo.class));
        assertEquals("redirect:/subgrupos/" + idGrupo, viewName);
    }

    @Test
    public void testDeleteGrupo() {
        Long idGrupo = 1L;

        // Mock del RedirectAttributes
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        // Caso exitoso sin excepción
        String viewName = gruposController.deleteGrupo(idGrupo, redirectAttributes);

        // Verificar que se llamó al método deleteGrupo del servicio una vez
        verify(grupoService, times(1)).deleteGrupo(idGrupo);
        // Verificar que se redirige correctamente después de intentar eliminar el grupo
        assertEquals("redirect:/grupos", viewName);

        // Verificar que no se añadió ningún mensaje de error al flash attributes
        verify(redirectAttributes, never()).addFlashAttribute(eq("errorMessage"), anyString());

        // Caso con excepción
        doThrow(new RuntimeException("No se puede eliminar el grupo")).when(grupoService).deleteGrupo(idGrupo);

        viewName = gruposController.deleteGrupo(idGrupo, redirectAttributes);

        // Verificar que se redirige correctamente después de intentar eliminar el grupo
        assertEquals("redirect:/grupos", viewName);

        // Verificar que el mensaje de error se añadió a RedirectAttributes
        verify(redirectAttributes, times(1)).addFlashAttribute("errorMessage", "No se puede eliminar el grupo porque está asociado o a un subgrupo, o a un niño o a un monitor.");
    }


    @Test
    public void testDeleteSubgrupo() {
        Long idSubgrupo = 1L;
        Long idGrupo = 1L;

        // Mock del subgrupo y su grupo asociado
        SubGrupo subGrupo = new SubGrupo();
        subGrupo.setIdSubgrupo(idSubgrupo);
        Grupo grupo = new Grupo();
        grupo.setIdGrupo(idGrupo);
        subGrupo.setGrupo(grupo);

        Optional<SubGrupo> subGrupoOptional = Optional.of(subGrupo);
        when(subGrupoService.findById(idSubgrupo)).thenReturn(subGrupoOptional);

        // Mock del RedirectAttributes
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        // Caso exitoso sin excepción
        String viewName = gruposController.deleteSubgrupo(idSubgrupo, redirectAttributes);
        verify(subGrupoService, times(1)).deleteSubGrupo(idSubgrupo);
        assertEquals("redirect:/subgrupos/" + idGrupo, viewName);

        // Verificar que no hay errores
        verify(redirectAttributes, never()).addFlashAttribute(eq("errorMessage"), anyString());

        // Caso con excepción
        doThrow(new RuntimeException("Error al eliminar subgrupo")).when(subGrupoService).deleteSubGrupo(idSubgrupo);

        viewName = gruposController.deleteSubgrupo(idSubgrupo, redirectAttributes);
        assertEquals("redirect:/subgrupos/" + idGrupo, viewName);

        // Verificar que el mensaje de error se añadió a RedirectAttributes
        verify(redirectAttributes, times(1)).addFlashAttribute("errorMessage", "No se puede eliminar el subgrupo porque está asociado a una tarea.");
    }

}

