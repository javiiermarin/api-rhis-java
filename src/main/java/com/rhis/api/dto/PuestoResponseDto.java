package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PuestoResponseDto {

    private String idPuesto;
    private String nombre;
    private Double salarioMaximo;
    private Double salarioMinimo;
    private DivisionResponseDto division;

}
