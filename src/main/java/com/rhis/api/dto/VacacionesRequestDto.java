package com.rhis.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
public class VacacionesRequestDto {

    @NotNull
    @JsonFormat(pattern = "yyy-MM-dd")
    private LocalDate fechaInicio;

    @NotNull
    @JsonFormat(pattern = "yyy-MM-dd")
    private LocalDate fechaFinal;

    @NotNull
    private String empleado;

}
