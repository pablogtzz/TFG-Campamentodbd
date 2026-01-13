package com.example.practicatfgbd.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Alergias")
public class Alergias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlergias;

    @Column(nullable = false)
    private String descripcion;
}
