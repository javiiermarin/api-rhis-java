package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmpleadoPermisoResponseDto {

    private String nombres;
    private String apellidos;
    private PuestoPermisoResponseDto puesto;
}
