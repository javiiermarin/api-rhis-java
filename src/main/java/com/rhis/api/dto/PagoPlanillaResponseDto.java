package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PagoPlanillaResponseDto {

    private String idPagoPlanilla;
    private Double salario;
    private Double bonificacionLey;
    private Double sueldoBase;
    private Double salarioNeto;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private EmpleadoPermisoResponseDto empleado;
}
