package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
public class MarcacionResponseDto {

    private String idMarcacion;
    private LocalTime horaEntrada;
    private LocalTime horaSalidaAlmuerzo;
    private LocalTime horaEntradaAlmuerzo;
    private LocalTime horaSalida;
    private LocalTime tiempoAntes;
    private String descripcion;
    private Boolean habilitado;

}
