package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermisoTrackingResponseDto {

    private boolean estado;
    private EmpleadoPermisoResponseDto empleado;
}
