package com.example.practicatfgbd.configuration;

import com.example.practicatfgbd.entities.Juegos;
import com.example.practicatfgbd.entities.Material;
import com.example.practicatfgbd.repositories.JuegosRepository;
import com.example.practicatfgbd.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;


@Configuration
public class MaterialConfig {
    @Autowired
    private JuegosRepository juegosRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Transactional
    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        initDatos();
    }

    public void initDatos() {

        /*Material material1 = Material.builder()
                .nombre("material1")
                .build();
        Material material2 = Material.builder()
                .nombre("material2")
                .build();
        Material material3 = Material.builder()
                .nombre("material3")
                .build();
        Material material4 = Material.builder()
                .nombre("material4")
                .build();
        materialRepository.saveAll(Arrays.asList(material1, material2, material3, material4));




        Juegos juego1 = Juegos.builder()
                .titulo("juego1")
                .tipo("dinamica")
                .titulo("Refelxion canciones")
                .duracion(30)
                .espacios("interior")
                .imagen("Hola")
                .materiales(Arrays.asList(material1, material2))
                .build();
        Juegos juego2 = Juegos.builder()
                .titulo("juego2")
                .tipo("dinamica")
                .titulo("Refelxion canciones")
                .duracion(30)
                .espacios("interior")
                .imagen("Hola")
                .materiales(Arrays.asList(material4, material2))
                .build();
        Juegos juego3 = Juegos.builder()
                .titulo("juego1")
                .tipo("dinamica")
                .titulo("Refelxion canciones")
                .duracion(30)
                .espacios("interior")
                .imagen("Hola")
                .materiales(Arrays.asList(material3, material4))
                .build();
        juegosRepository.saveAll(Arrays.asList(juego1, juego2, juego3));*/
    }
}
