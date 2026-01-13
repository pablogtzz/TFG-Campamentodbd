package com.example.practicatfgbd.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Subgrupo")
public class SubGrupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubgrupo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int numeroIntegrantes;

    @ManyToOne
    @JoinColumn(name = "ID_Grupo")
    private Grupo grupo;
}
