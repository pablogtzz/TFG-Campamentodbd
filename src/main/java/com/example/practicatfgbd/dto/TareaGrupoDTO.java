package com.example.practicatfgbd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TareaGrupoDTO {
    private String nombre;
    private String descripcion;
    private LocalDate dia;
    private Long grupoId;
    private Long userId;

}
