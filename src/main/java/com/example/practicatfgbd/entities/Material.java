package com.example.practicatfgbd.entities;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmaterial")
    private Long idMaterial;

    @Column(nullable = false,name = "nombre")
    private String nombre;
}
