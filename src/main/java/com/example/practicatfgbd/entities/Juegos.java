package com.example.practicatfgbd.entities;
import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Juegos")
public class Juegos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJuegos;

    @Enumerated(EnumType.STRING)
    private TipoActividad tipo;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private int duracion;

    @Column(nullable = false)
    private String espacios;

    @Column(nullable = false)
    private String imagenRuta;

    @Lob
    @Column(nullable = false)
    private String descripcion;

    @ManyToMany
    @JoinTable(
            name = "material_juegos",
            joinColumns = @JoinColumn(name = "idjuegos"),
            inverseJoinColumns = @JoinColumn(name = "idmaterial")
    )
    private List<Material> materiales;
}
