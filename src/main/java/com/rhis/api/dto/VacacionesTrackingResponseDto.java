package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VacacionesTrackingResponseDto {

    private String idVacacionesTracking;
    private boolean estado;
    private EmpleadoPermisoResponseDto empleadoPermisoResponseDto;
}
