package com.rhis.api.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String descripcion;
    private EmpleadoPermisoResponseDto empleado;
    @JsonManagedReference
    private List<VacacionesTrackingResponseDto> vacacionesTracking;

}
