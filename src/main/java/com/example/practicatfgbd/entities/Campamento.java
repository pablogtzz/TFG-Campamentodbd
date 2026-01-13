package com.example.practicatfgbd.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Campamento")
public class Campamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCampamento;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @Min(1)
    @Column(nullable = false)
    private int capacidad;

    @Min(0)
    @Column(nullable = false)
    private double precio;

    @NotBlank
    @Column(nullable = false)
    private String lugar;

    @Column(nullable = false)
    private Date fechas;
}