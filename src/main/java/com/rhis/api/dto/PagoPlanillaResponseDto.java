package com.rhis.api.dto;

import com.rhis.api.model.Empleado;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PagoPlanillaResponseDto {


    private Double salario;
    private Double bonificacionLey;
    private Double sueldoBase;
    private Double sueldoNeto;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinal;
    private Empleado empleado;
}
