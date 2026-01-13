package com.example.practicatfgbd.entities;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotificacion;

    @Column(nullable = false)
    private String contenido;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private boolean leido;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;
}

