package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Setter
@Getter
public class MarcacionEmpleadoResponseDto {

    private String idMarcacionEmpleado;
    private EmpleadoPermisoResponseDto empleado;
    private LocalTime horaEntrada;
    private LocalTime horaSalidaAlmuerzo;
    private LocalTime horaEntradaAlmuerzo;
    private LocalTime horaSalida;
    private LocalDate fecha;
}
