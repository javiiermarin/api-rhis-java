package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class HoraExtraResponseDto {

    private String idHoraExtra;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFinal;
    private LocalDate fecha;
    private EmpleadoResponseDto empleado;
}
