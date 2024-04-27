package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class PermisoResponseDto {

    private String idPermiso;
    private String descripcion;
    private LocalDateTime fechaSolicitud;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private TipoPermisoResposeDto tipoPermiso;
    private EmpleadoPermisoResponseDto empleado;
    private List<PermisoTrackingResponseDto> permisoTracking;
}
