package com.rhis.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
public class MarcacionRequestDto {

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaEntrada;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaSalidaAlmuerzo;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaEntradaAlmuerzo;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaSalida;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime tiempoAntes;

    @Size(max = 100)
    private String descripcion;

    private Boolean habilitado;
}
