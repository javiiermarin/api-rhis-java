package com.rhis.api.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class MarcacionRandomResponseDto {

    private LocalTime hora;
    private LocalDate fecha;
    private EmpleadoPermisoResponseDto empleado;
}
