package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExperienciaLaboralResponseDto {

    private String nombreEmpresa;
    private int antiguedad;
    private String telefono;
    private String puesto;
    private String descripcion;
}
