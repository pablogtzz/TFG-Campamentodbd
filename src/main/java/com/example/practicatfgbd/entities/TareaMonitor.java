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
@Table(name = "Tarea_Monitor")
public class TareaMonitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTareaMonitor;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate dia;

    @Enumerated(EnumType.STRING)
    private EstadoTarea estadoTarea;

    @ManyToOne
    @JoinColumn(name = "Id_user")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "Tarea_Material_Monitor",
            joinColumns = @JoinColumn(name = "ID_Tarea_Monitor"),
            inverseJoinColumns = @JoinColumn(name = "ID_Material")
    )
    private List<Material> materiales;
}
