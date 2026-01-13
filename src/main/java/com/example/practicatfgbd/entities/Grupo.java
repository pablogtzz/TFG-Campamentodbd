package com.example.practicatfgbd.entities;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Grupo")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGrupo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int edades;
}
