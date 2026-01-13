package com.example.practicatfgbd.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "Kids")
public class Kids {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idKids;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String tallaCamiseta;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private String nombrePadre;

    @Column(nullable = false)
    private String telefonoPadre;

    @Column(nullable = false)
    private String nombreMadre;

    @Column(nullable = false)
    private String telefonoMadre;

    @ManyToOne
    @JoinColumn(name = "ID_Grupo")
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "ID_Subgrupo")
    private SubGrupo subgrupo;

    @ManyToMany
    @JoinTable(
            name = "Kids_Alergias",
            joinColumns = @JoinColumn(name = "ID_Kids"),
            inverseJoinColumns = @JoinColumn(name = "ID_Alergias")
    )
    private List<Alergias> alergias;
}
