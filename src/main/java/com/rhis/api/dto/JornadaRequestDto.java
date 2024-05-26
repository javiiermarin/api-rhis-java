package com.rhis.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
public class JornadaRequestDto {

    @Size(max = 36)
    private String idJornada;

    @NotBlank
    @Size(max = 30)
    private String jornada;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaInicio;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaFin;

    @NotNull
    private Double valor;
}
