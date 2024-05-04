package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class VacacionesResponseDto {

    private String idVacaciones;
    private LocalDateTime fechaSolicitud;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private EmpleadoPermisoResponseDto empleado;
    private List<VacacionesTrackingResponseDto> vacacionesTracking;

}
