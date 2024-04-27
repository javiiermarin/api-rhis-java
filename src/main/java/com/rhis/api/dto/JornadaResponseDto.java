package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
public class JornadaResponseDto {

    private String idJornada;
    private String jornada;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Double valor;
}
