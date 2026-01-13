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
@Table(name = "Tarea_Kids")
public class TareaKids {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTareaKids;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate dia;

    @Enumerated(EnumType.STRING)
    private EstadoTarea estadoTarea;

    @ManyToOne
    @JoinColumn(name = "Id_subgrupo")
    private SubGrupo subGrupo;

    @ManyToOne
    @JoinColumn(name = "Id_user")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "Tarea_Material",
            joinColumns = @JoinColumn(name = "ID_Tarea_Kids"),
            inverseJoinColumns = @JoinColumn(name = "ID_Material")
    )
    private List<Material> materiales;
}
